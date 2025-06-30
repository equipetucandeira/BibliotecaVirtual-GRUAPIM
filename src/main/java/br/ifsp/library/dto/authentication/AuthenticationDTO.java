package br.ifsp.library.dto.authentication;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthenticationDTO {
  @NotBlank(message = "Please, enter your username.")
  private String email;
  @NotBlank(message = "Please, enter your password.")
  private String password;

  /*public AuthenticationDTO(String email, String password) {
    this.email = email;
    this.password = password;
  }*/

}
