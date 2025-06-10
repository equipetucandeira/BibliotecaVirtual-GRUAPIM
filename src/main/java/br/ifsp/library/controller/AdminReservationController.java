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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import br.ifsp.library.dto.ReservationResponseDTO;

@Validated
@RestController
@RequestMapping("/api/admin/reservations")
public class AdminReservationController {

  @Autowired
  private ReservationService reservationService;

  @Operation(summary = "Buscar por todas as reservas", description = "Retorna todas as reservas do sistema")
  @ApiResponses(value = {

      @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

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

  @Operation(summary = "Buscar por todas as reservas ativas", description = "Retorna todas as reservas ativas do sistema")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

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
