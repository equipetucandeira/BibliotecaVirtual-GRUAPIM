package br.ifsp.library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ifsp.library.model.*;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  @Query("SELECT COUNT(r) FROM Reservation r WHERE r.book.id = :bookId AND r.active = true AND CURRENT_DATE BETWEEN r.startDate AND r.endDate")
  long countActiveReservationsByBookId(@Param("bookId") Long bookId);

  Page<Reservation> findAll(Pageable pageable);

  Page<Reservation> findByUserEmail(String email, Pageable pageable);

  Page<Reservation> findByActive(Boolean active, Pageable pageable);
}
