package br.ifsp.library.service;

import br.ifsp.library.dto.ReservationResponseDTO;
import br.ifsp.library.exception.BadRequestException;
import br.ifsp.library.exception.ResourceNotFoundException;
import br.ifsp.library.model.User;
import br.ifsp.library.repository.BookRepository;
import br.ifsp.library.repository.ReservationRepository;
import br.ifsp.library.repository.UserRepository;
import br.ifsp.library.model.Book;
import br.ifsp.library.model.Reservation;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.domain.PageRequest;

@Service
public class ReservationService {

  @Autowired
  private ReservationRepository reservationRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BookRepository bookRepository;
  
  @Autowired
  private ModelMapper model;


  public Page<ReservationResponseDTO> getAllReservation(int page, int size, String sortBy) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
    Page<Reservation> reservation = reservationRepository.findAll(pageable);
    if (reservation == null) {
      return Page.empty(pageable);
    }
    return reservation.map(ReservationResponseDTO::new);
  }

  public Page<ReservationResponseDTO> getUserReservations(int page, int size, String sortBy, String name) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
    Page<ReservationResponseDTO> reservation = reservationRepository.findByUserName(name, pageable);
    if (reservation == null) {
      return Page.empty(pageable);
    }
    
    return reservation;

  }
  
  public Page<ReservationResponseDTO> getActiveReservations(boolean active, int page, int size, String sortBy) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    Page<Reservation> reservation = reservationRepository.findByActive(active, pageable);
    if (reservation == null) {
        return Page.empty(pageable);
      }
      return reservation.map(ReservationResponseDTO::new);
  }

  public Reservation getReservationById(Long id) {
    Reservation reservation = reservationRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with ID: " + id));
    return reservation;
  }

  public ReservationResponseDTO reservBook(Long bookId, String name) {
    User user = userRepository.findByName(name)
        .orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado"));

    Book book = bookRepository.findById(bookId)
        .orElseThrow(() -> new ResourceNotFoundException("Livro nao encontrado"));

    long currentlyReservations = reservationRepository.countActiveReservationsByBookId(bookId);

    if (currentlyReservations >= book.getQuantity()) {
      throw new BadRequestException("Livro indisponível para reserva.");

    }

    Reservation reservation = new Reservation();
    reservation.setBook(book);
    reservation.setUser(user);
    reservation.setStartDate(LocalDate.now());
    reservation.setEndDate(LocalDate.now().plusDays(7));
    reservation.setActive(true);
    reservation.transformResponseDTO();
    reservationRepository.save(reservation);
    return reservation.transformResponseDTO();
  }

  public void devolution(Long reservationId) {
    Reservation reservation = reservationRepository.findById(reservationId)
        .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada"));

    if (!reservation.isActive()) {
      throw new BadRequestException("Essa reserva já foi encerrada.");
    }

    reservation.setActive(false);
    reservation.setEndDate(LocalDate.now());
    reservationRepository.save(reservation);
    
  }
}
