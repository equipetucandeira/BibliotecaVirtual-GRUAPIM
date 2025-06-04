package br.ifsp.library.dto;

import br.ifsp.library.model.RoleType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class UserRequestDTO {
	
	@NotBlank(message = "Name is required")
	private String name;
	@NotBlank(message = "Email is required")
	private String email;
	private RoleType role;
	
}
