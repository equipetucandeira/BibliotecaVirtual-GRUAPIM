package br.ifsp.library.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import br.ifsp.library.dto.authentication.UserRegistrationDTO;
import br.ifsp.library.dto.authentication.UserResponseDTO;
import br.ifsp.library.service.UserService;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("/api/users")
public class UserController {
  @Autowired
  private UserService userService;

  @Operation(summary = "Registra um usuario no sistema", description = "Retorna se o usuario foi registrado com sucesso")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @PostMapping("/register")
  public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRegistrationDTO user) {
    return ResponseEntity.ok(userService.createUser(user));
  }

}
