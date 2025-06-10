package br.ifsp.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;

import org.springframework.data.domain.Page;

import br.ifsp.library.dto.BookRequestDTO;
import br.ifsp.library.dto.BookResponseDTO;
import br.ifsp.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/admin/books")
@PreAuthorize("hasRole('ADMIN')")
public class AdminBookController {
  @Autowired
  private BookService bookService;

  @Operation(summary = "Buscar todos os livros da biblioteca", description = "Retorna uma lista paginada de livros ordenados por um campo especifico")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @GetMapping
  public ResponseEntity<Page<BookResponseDTO>> getAllBooks(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "title") String sortBy) {
    Page<BookResponseDTO> books = bookService.getAllBooks(page, size, sortBy);
    if (books.isEmpty())
      return ResponseEntity.noContent().build();
    return ResponseEntity.ok(books);
  }

  @Operation(summary = "Adicionar um livro da biblioteca", description = "Retorna se o livro foi criado com sucesso")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)

  })
  @PostMapping
  public ResponseEntity<BookResponseDTO> createBook(@RequestBody @Valid BookRequestDTO dto) {
    return ResponseEntity.ok(bookService.createBook(dto));
  }

  @Operation(summary = "Remover um livro da biblioteca", description = "Retorna se o livro foi removido com sucesso")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    bookService.deleteBook(id);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Atualizar caracteristicas de um livro da biblioteca", description = "Retorna se o livro foi parcialmente alterado com sucesso")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @PatchMapping("/{id}")
  public ResponseEntity<BookResponseDTO> patchBook(@PathVariable Long id, @RequestBody @Valid BookRequestDTO dto) {
    return ResponseEntity.ok(bookService.updateBook(id, dto));
  }

  @Operation(summary = "Atualizar um livro da biblioteca", description = "Retorna se o livro foi atualizado com sucesso")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @PutMapping("/{id}")
  public ResponseEntity<BookResponseDTO> updateTask(@PathVariable Long id, @RequestBody @Valid BookRequestDTO dto) {
    return ResponseEntity.ok(bookService.updateBook(id, dto));
  }

}
