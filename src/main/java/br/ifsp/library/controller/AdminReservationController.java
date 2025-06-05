package br.ifsp.library.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Page;

import br.ifsp.library.service.ReservationService;
import br.ifsp.library.dto.ReservationResponseDTO;

@Validated
@RestController
@RequestMapping("/api/admin/reservations")
public class AdminReservationController {

  @Autowired
  private ReservationService reservationService;

  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Page<ReservationResponseDTO>> getAllReservations(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "id") String sortBy) {
    Page<ReservationResponseDTO> reservation = reservationService.getAllReservation(page, size, sortBy);
    if (reservation.isEmpty())
      return ResponseEntity.noContent().build();
    return ResponseEntity.ok(reservation);
  }

  @GetMapping("/active")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Page<ReservationResponseDTO>> getActiveReservations(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "title") String sortBy, Boolean active) {
    Page<ReservationResponseDTO> reservation = reservationService.getActiveReservations(active, page, size, sortBy);
    if (reservation.isEmpty())
      return ResponseEntity.noContent().build();
    return ResponseEntity.ok(reservation);
  }

}
