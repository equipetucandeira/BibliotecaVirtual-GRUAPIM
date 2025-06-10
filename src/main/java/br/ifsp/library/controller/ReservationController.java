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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;

import br.ifsp.library.service.ReservationService;
import br.ifsp.library.dto.ReservationResponseDTO;
import br.ifsp.library.model.Reservation;

@Validated
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

  @Autowired
  private ReservationService reservationService;

  @Operation(summary = "Busca as reservas pelo ID passado", description = "Retorna as informações da reserva buscada pelo ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @GetMapping("/{id}")
  public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
    Reservation reservation = reservationService.getReservationById(id);
    return ResponseEntity.ok(reservation);
  }

  @Operation(summary = "Busca pelas reservas do usuario", description = "Retorno das reservas feitas por um usuario especifico")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

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

  @Operation(summary = "Remove um livro da lista de reservas", description = "Retorna se o livro foi devolvido com sucesso")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @PostMapping("/{reservationId}/devolution")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<String> returnBook(@PathVariable Long reservationId) {
    reservationService.devolution(reservationId);
    return ResponseEntity.ok("Livro devolvido com sucesso");
  }

}
