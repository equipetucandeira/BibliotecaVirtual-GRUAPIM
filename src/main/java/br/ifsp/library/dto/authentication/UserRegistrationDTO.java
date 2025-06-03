package br.ifsp.library.dto.authentication;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class UserRegistrationDTO {
	@NotBlank(message = "Name is required")
	private String name;
	@NotBlank(message = "Email is required")
	private String email;
	@NotBlank(message = "Password is required")
	private String password;
}
