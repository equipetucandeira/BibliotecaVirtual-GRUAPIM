package br.ifsp.library.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Page;



import br.ifsp.library.service.ReservationService;
import br.ifsp.library.dto.ReservationResponseDTO;
import br.ifsp.library.model.Reservation;

@Validated
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {


  @Autowired
  private ReservationService reservationService;

  @GetMapping("/{id}")
  public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
    Reservation reservation = reservationService.getReservationById(id);
    return ResponseEntity.ok(reservation);
  }

  @GetMapping("/user")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<Page<ReservationResponseDTO>> getMyReservations(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "startDate") String sortBy,
      Authentication authentication) {
    String username = authentication.getName();
    Page<ReservationResponseDTO> reservations = reservationService.getUserReservations(page, size, sortBy, username);
    return ResponseEntity.ok(reservations);
  }

  @PostMapping("/{reservationId}/devolution")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<String> returnBook(@PathVariable Long reservationId) {
    reservationService.devolution(reservationId);
    return ResponseEntity.ok("Livro devolvido com sucesso");
  }

}
