package br.ifsp.library.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ifsp.library.dto.MostBorrowedDTO;
import br.ifsp.library.dto.ReservationResponseDTO;
import br.ifsp.library.model.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

  @Query("SELECT COUNT(r) FROM Reservation r WHERE r.book.id = :bookId AND r.active = true")
  long countActiveReservationsByBookId(@Param("bookId") Long bookId);

  Page<Reservation> findAll(Pageable pageable);

  Page<Reservation> findByUserEmail(String email, Pageable pageable);
  
  Page<ReservationResponseDTO> findByUserName(String name, Pageable pageable);

  Page<Reservation> findByActive(Boolean active, Pageable pageable);

  @Query("SELECT new br.ifsp.library.dto.MostBorrowedDTO(r.book.title, COUNT(r)) " + "FROM Reservation r "
      + "GROUP BY r.book.title " + "ORDER BY COUNT(r) DESC")
  List<MostBorrowedDTO> findMostBorrowedBooks();

  long countByStartDateBetween(LocalDate startDate, LocalDate endDate);

  @Query("SELECT COUNT(DISTINCT r.user.id) FROM Reservation r WHERE r.startDate BETWEEN :start AND :end")
  long countDistinctUsersByStartDateBetween(LocalDate start, LocalDate end);

  @Query("SELECT new br.ifsp.library.dto.MostBorrowedDTO(r.book.title, COUNT(r)) " + "FROM Reservation r "
      + "WHERE r.startDate BETWEEN :start AND :end " + "GROUP BY r.book.title " + "ORDER BY COUNT(r) DESC")
  List<MostBorrowedDTO> findMostBorrowedBooksByPeriod(LocalDate start, LocalDate end);
}
