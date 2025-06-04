package br.ifsp.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ifsp.library.dto.MostBorrowedDTO;
import br.ifsp.library.service.ReportService;

@RestController
@RequestMapping("/api/report/")
public class ReportController {
  @Autowired
  ReportService reportService;

  @GetMapping("/mostBorrowed")
  public ResponseEntity<List<MostBorrowedDTO>> reportLoans() {
    return ResponseEntity.ok(reportService.reportLoans());
  }

  /*
   * @GetMapping("/libUse")
   * public ResponseEntity<LibUseDTO>
   * libUse(@RequestParam("startDate") @DateTimeFormat(iso =
   * DateTimeFormat.ISO.DATE) LocalDate startDate,
   * 
   * @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
   * LocalDate endDate){
   * return ResponseEntity.ok(reportService.reportLibUse(startDate, endDate));
   * }
   */
}
