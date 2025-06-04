package br.ifsp.library.dto.authentication;

import br.ifsp.library.model.RoleType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class UserResponseDTO {
	private Long id;
	@NotBlank(message = "Name is required")
	private String name;
	@NotBlank(message = "Email is required")
	private String email;
	private RoleType role;
	public UserResponseDTO(String name, String email, RoleType role, Long id){
		this.name = name;
		this.email = email;
		this.role = role;
		this.id = id;
	}

}
