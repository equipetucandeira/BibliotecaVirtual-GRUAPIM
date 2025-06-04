package br.ifsp.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

//import com.ifsp.task.dto.TaskRequestDTO;
//import com.ifsp.task.dto.TaskResponseDTO;
//import com.ifsp.task.service.TaskService;
import br.ifsp.library.dto.BookResponseDTO;
import br.ifsp.library.dto.BookRequestDTO;

import br.ifsp.library.service.BookService;
import br.ifsp.library.service.ReservationService;
import br.ifsp.library.model.Reservation;

@Validated
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

  // @Autowired
  // private BookService bookService;

  @Autowired
  private ReservationService reservationService;

  @GetMapping("/{id}")
  public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
    Reservation reservation = reservationService.getReservationById(id);
    return ResponseEntity.ok(reservation);
  }

  @GetMapping("/user")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<Page<Reservation>> getMyReservations(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "startDate") String sortBy,
      Authentication authentication) {
    String username = authentication.getName();
    Page<Reservation> reservations = reservationService.getUserReservations(page, size, sortBy, username);
    return ResponseEntity.ok(reservations);
  }

  @PostMapping("/{reservationId}/devolution")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<Void> returnBook(@PathVariable Long reservationId) {
    reservationService.devolution(reservationId);
    return ResponseEntity.noContent().build();
  }

  // @GetMapping("/catalog")
  // public ResponseEntity<Page<BookResponseDTO>> getAvailableBooks(
  // @RequestParam(defaultValue = "0") int page,
  // @RequestParam(defaultValue = "10") int size,
  // @RequestParam(defaultValue = "title") String sortBy) {
  // return ResponseEntity.ok(bookService.getAvailableBooks(page, size, sortBy));
  // }

  // @GetMapping("/search")
  // public ResponseEntity<Page<BookResponseDTO>> getBooksByAuthor(@RequestParam
  // String author,
  // @RequestParam(defaultValue = "0") int page,
  // @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue =
  // "title") String sortBy) {
  // return ResponseEntity.ok(bookService.getBooksByAuthor(page, size, sortBy,
  // author));

  // }

  // @PostMapping
  // public ResponseEntity<BookResponseDTO> createBook(@RequestBody @Valid
  // BookRequestDTO dto) {
  // return ResponseEntity.ok(bookService.createBook(dto));
  // }

  // @PostMapping("/{bookId}/reserve")
  // public ResponseEntity<?> reserveBook(
  // @PathVariable Long bookId,
  // Authentication authentication) {

  // if (authentication == null || !authentication.isAuthenticated()) {
  // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  // }

  // String username = authentication.getName(); // ou pegar via JWT claims
  // reservationService.reservBook(bookId, username);
  // return ResponseEntity.ok().build();
  // }

  //

  // @DeleteMapping("/{id}")
  // public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
  // bookService.deleteBook(id);
  // return ResponseEntity.noContent().build();
  // }

  // @PatchMapping("/{id}")
  // public ResponseEntity<BookResponseDTO> patchTask(@PathVariable Long id,
  // @RequestBody @Valid BookRequestDTO dto) {
  // return ResponseEntity.ok(bookService.updateBook(id, dto));
  // }

  // @PutMapping("/{id}")
  // public ResponseEntity<BookResponseDTO> updateTask(@PathVariable Long id,
  // @RequestBody @Valid BookRequestDTO dto) {
  // return ResponseEntity.ok(bookService.updateBook(id, dto));
  // }

}
