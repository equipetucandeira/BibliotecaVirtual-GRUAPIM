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
import org.springframework.http.HttpStatus;

//import com.ifsp.task.dto.TaskRequestDTO;
//import com.ifsp.task.dto.TaskResponseDTO;
//import com.ifsp.task.service.TaskService;
import br.ifsp.library.dto.BookResponseDTO;

import br.ifsp.library.service.BookService;
import br.ifsp.library.service.ReservationService;

@Validated
@RestController
@RequestMapping("/api/books")
public class BookController {

  @Autowired
  private BookService bookService;

  @Autowired
  private ReservationService reservationService;

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/{id}")
  public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) {
    BookResponseDTO book = bookService.getBookById(id);
    return ResponseEntity.ok(book);
  }

  @GetMapping("/catalog")
  public ResponseEntity<Page<BookResponseDTO>> getAvailableBooks(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "title") String sortBy) {
    return ResponseEntity.ok(bookService.getAvailableBooks(page, size, sortBy));
  }

  @GetMapping("/search")
  public ResponseEntity<Page<BookResponseDTO>> getBooksByAuthor(@RequestParam String author,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "title") String sortBy) {
    return ResponseEntity.ok(bookService.getBooksByAuthor(page, size, sortBy, author));

  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/{bookId}/reserve")
  public ResponseEntity<?> reserveBook(
      @PathVariable Long bookId,
      Authentication authentication) {

    if (authentication == null || !authentication.isAuthenticated()) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    String username = authentication.getName(); // ou pegar via JWT claims
    return ResponseEntity.ok(reservationService.reservBook(bookId, username));
  }

}
