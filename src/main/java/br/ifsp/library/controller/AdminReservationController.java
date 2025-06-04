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
@RequestMapping("/api/admin/reservations")
public class AdminReservationController {

  @Autowired
  private ReservationService reservationService;

  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Page<Reservation>> getAllReservations(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "title") String sortBy) {
    Page<Reservation> reservation = reservationService.getAllReservation(page, size, sortBy);
    if (reservation.isEmpty())
      return ResponseEntity.noContent().build();
    return ResponseEntity.ok(reservation);
  }

  @GetMapping("/active")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Page<Reservation>> getActiveReservations(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "title") String sortBy, Boolean active) {
    Page<Reservation> reservation = reservationService.getActiveReservations(active, page, size, sortBy);
    if (reservation.isEmpty())
      return ResponseEntity.noContent().build();
    return ResponseEntity.ok(reservation);
  }

}
