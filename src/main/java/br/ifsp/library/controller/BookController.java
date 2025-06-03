package br.ifsp.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

//import com.ifsp.task.dto.TaskRequestDTO;
//import com.ifsp.task.dto.TaskResponseDTO;
//import com.ifsp.task.service.TaskService;
import br.ifsp.library.dto.BookResponseDTO;
import br.ifsp.library.dto.BookRequestDTO;

import br.ifsp.library.service.BookService;

@Validated
@RestController
@RequestMapping("/api/books")
public class BookController {

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

  @GetMapping("/{id}")
  public ResponseEntity<BookResponseDTO> getTaskById(@PathVariable Long id) {
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

  @PostMapping
  public ResponseEntity<BookResponseDTO> createTask(@RequestBody @Valid BookRequestDTO dto) {
    return ResponseEntity.ok(bookService.createBook(dto));
  }

  // @PatchMapping("/{id}/concluir")
  // public ResponseEntity<TaskResponseDTO> concludeTask(@PathVariable Long id) {
  // return ResponseEntity.ok(taskService.concludeTask(id));
  // }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    bookService.deleteBook(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<BookResponseDTO> patchTask(@PathVariable Long id, @RequestBody @Valid BookRequestDTO dto) {
    return ResponseEntity.ok(bookService.updateBook(id, dto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<BookResponseDTO> updateTask(@PathVariable Long id, @RequestBody @Valid BookRequestDTO dto) {
    return ResponseEntity.ok(bookService.updateBook(id, dto));
  }

}
