package br.ifsp.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;

import br.ifsp.library.dto.UserRequestDTO;
import br.ifsp.library.dto.authentication.UserResponseDTO;
import br.ifsp.library.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

  @Autowired
  private UserService userService;

  @Operation(summary = "Buscar por todas os usuarios do sistema", description = "Retorna todos os usuarios do sistema")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })
  @GetMapping("/all")
  public ResponseEntity<List<UserResponseDTO>> getUsers() {
    return ResponseEntity.ok(userService.getAll());
  }

  @Operation(summary = "Deletar um usuarios especifico", description = "Retorna se o usuário foi removido com sucesso")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Atualizar parcialmente um usuario do sistema", description = "Retorna se o usuário foi atualizado com sucesso")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @PatchMapping("/{id}")
  public ResponseEntity<UserResponseDTO> patchUser(@PathVariable Long id, @RequestBody @Valid UserRequestDTO dto) {
    return ResponseEntity.ok(userService.updateUser(id, dto));
  }

  @Operation(summary = "Atualiza um usuário especifico", description = "Retorna se o usuario foi atualizado com sucesso")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @PutMapping("/{id}")
  public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserRequestDTO dto) {
    return ResponseEntity.ok(userService.updateUser(id, dto));
  }

  @Operation(summary = "Eleva a role do usuario padrão", description = "Retorna se o usuário ganhou as permissões com sucesso")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @PatchMapping("/elevate/{id}")
  public ResponseEntity<UserResponseDTO> elevateUser(@PathVariable Long id) {
    return ResponseEntity.ok(userService.elevateUser(id));
  }
}
