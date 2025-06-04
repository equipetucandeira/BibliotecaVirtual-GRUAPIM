package br.ifsp.library.controller;

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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import br.ifsp.library.dto.BookRequestDTO;
import br.ifsp.library.dto.BookResponseDTO;
import br.ifsp.library.service.BookService;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/admin/books")
@PreAuthorize("hasRole('ADMIN')")
public class AdminBookController {
  @Autowired
  private BookService bookService;

  @GetMapping
  public ResponseEntity<Page<BookResponseDTO>> getAllBooks(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "title") String sortBy) {
    Page<BookResponseDTO> books = bookService.getAllBooks(page, size, sortBy);
    if (books.isEmpty())
      return ResponseEntity.noContent().build();
    return ResponseEntity.ok(books);
  }

  @PostMapping
  public ResponseEntity<BookResponseDTO> createBook(@RequestBody @Valid BookRequestDTO dto) {
    return ResponseEntity.ok(bookService.createBook(dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    bookService.deleteBook(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<BookResponseDTO> patchBook(@PathVariable Long id, @RequestBody @Valid BookRequestDTO dto) {
    return ResponseEntity.ok(bookService.updateBook(id, dto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<BookResponseDTO> updateTask(@PathVariable Long id, @RequestBody @Valid BookRequestDTO dto) {
    return ResponseEntity.ok(bookService.updateBook(id, dto));
  }

}
