package br.ifsp.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Page;

import br.ifsp.library.dto.BookFilterDto;
import br.ifsp.library.dto.BookResponseDTO;
import br.ifsp.library.dto.ReservationResponseDTO;
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

  @Operation(summary = "Busca o Livro pelo seu ID", description = "Retorna o livro buscado pelo ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/{id}")
  public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) {
    BookResponseDTO book = bookService.getBookById(id);
    return ResponseEntity.ok(book);
  }

  @Operation(summary = "Busca o Livro e permite adicionar filtros", description = "Retorna os livros buscados pelos filtros")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/search")
  public ResponseEntity<Page<BookResponseDTO>> searchBooks(
      @ModelAttribute BookFilterDto filter,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "title") String sort) {

    Page<BookResponseDTO> result = bookService.filterBooks(filter, page, size, sort);

    return ResponseEntity.ok(result);
  }

  @Operation(summary = "Busca por livros disponiveis", description = "Retorna os livros disponiveis na biblioteca")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })
  @GetMapping("/catalog")
  public ResponseEntity<Page<BookResponseDTO>> getAvailableBooks(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "title") String sortBy) {
    return ResponseEntity.ok(bookService.getAvailableBooks(page, size, sortBy));
  }

  @Operation(summary = "Busca os livros pelo autor", description = "Retorna o livro buscado pelo Autor")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  /*
   * @GetMapping("/search/author")
   * public ResponseEntity<Page<BookResponseDTO>> getBooksByAuthor(@RequestParam
   * String author,
   * 
   * @RequestParam(defaultValue = "0") int page,
   * 
   * @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue =
   * "title") String sortBy) {
   * return ResponseEntity.ok(bookService.getBooksByAuthor(page, size, sortBy,
   * author));
   * 
   * }
   * 
   * @Operation(summary = "Busca os livros pelo titulo", description =
   * "Retorna o livro buscado pelo Titulo")
   * 
   * @ApiResponses(value = {
   * 
   * @ApiResponse(responseCode = "401", description = "Não autorizado", content
   * = @Content),
   * 
   * @ApiResponse(responseCode = "200", description =
   * "Endereços retornados com sucesso"),
   * 
   * @ApiResponse(responseCode = "400", description = "Parâmetros inválidos",
   * content = @Content),
   * 
   * @ApiResponse(responseCode = "500", description = "Erro interno", content
   * = @Content)
   * })
   * 
   * @GetMapping("/search/title")
   * public ResponseEntity<Page<BookResponseDTO>> getBooksByTitle(@RequestParam
   * String title,
   * 
   * @RequestParam(defaultValue = "0") int page,
   * 
   * @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue =
   * "title") String sortBy) {
   * return ResponseEntity.ok(bookService.getBooksByTitle(page, size, sortBy,
   * title));
   * 
   * }
   * 
   * @Operation(summary = "Reserva um livro especifico", description =
   * "Retorna a reserva com sucesso")
   * 
   * @ApiResponses(value = {
   * 
   * @ApiResponse(responseCode = "401", description = "Não autorizado", content
   * = @Content),
   * 
   * @ApiResponse(responseCode = "200", description =
   * "Endereços retornados com sucesso"),
   * 
   * @ApiResponse(responseCode = "400", description = "Parâmetros inválidos",
   * content = @Content),
   * 
   * @ApiResponse(responseCode = "500", description = "Erro interno", content
   * = @Content)
   * })
   */

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/{bookId}/reserve")
  public ResponseEntity<ReservationResponseDTO> reserveBook(
      @PathVariable Long bookId,
      Authentication authentication) {

    String username = authentication.getName(); // ou pegar via JWT claims
    return ResponseEntity.ok(reservationService.reservBook(bookId, username));
  }

}
