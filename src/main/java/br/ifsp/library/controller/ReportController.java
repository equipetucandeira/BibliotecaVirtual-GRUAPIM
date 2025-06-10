package br.ifsp.library.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;

import br.ifsp.library.dto.LibUseDTO;
import br.ifsp.library.dto.MostBorrowedDTO;
import br.ifsp.library.service.ReportService;

@RestController
@RequestMapping("/api/admin/report/")
public class ReportController {
  @Autowired
  ReportService reportService;

  @Operation(summary = "Retorna dados dos empréstimos mais feitos", description = "Retorno de dados dos livros mais reservados")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @GetMapping("/mostBorrowed")
  public ResponseEntity<List<MostBorrowedDTO>> reportLoans() {
    return ResponseEntity.ok(reportService.reportLoans());
  }

  @Operation(summary = "Busca por livros mais reservados em um periodo especifico", description = "Retorno os livros mais reservados neste periodo")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @GetMapping("/libUse")
  public ResponseEntity<LibUseDTO> libUse(
      @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
      @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
    return ResponseEntity.ok(reportService.reportLibUse(startDate, endDate));
  }
}
