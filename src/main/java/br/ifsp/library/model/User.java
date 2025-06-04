package br.ifsp.library.model;

import java.util.List;
import java.util.stream.Collectors;

import br.ifsp.library.dto.authentication.UserResponseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Name is required")
	private String name;
	@NotBlank(message = "Email is required")
	private String email;
	@NotBlank(message = "Password is required")
	private String password;
  @Enumerated(EnumType.STRING)
  private RoleType role;
	
	public User() {
		
	}
	
	public User(String name, String email, String password, RoleType role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public UserResponseDTO transformDto(User user) {
		UserResponseDTO dto = new UserResponseDTO(user.getName(), user.getEmail(), user.getRole(), user.getId());
		return dto;
	}
	
	public static List<UserResponseDTO> transformListDto(List<User> users){
		return users.stream()
				 .map(user -> new UserResponseDTO(user.getName(), user.getEmail(), user.getRole(), user.getId()))
                .collect(Collectors.toList());
	}
	
	public RoleType getRole() {
		return role;
	}
	public void setRole(RoleType role) {
		this.role = role;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
