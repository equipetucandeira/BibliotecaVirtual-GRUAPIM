package br.ifsp.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;


import br.ifsp.library.dto.UserRequestDTO;
import br.ifsp.library.dto.authentication.UserRegistrationDTO;
import br.ifsp.library.dto.authentication.UserResponseDTO;
import br.ifsp.library.service.UserService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRegistrationDTO user) {
		return ResponseEntity.ok(userService.createUser(user));
	}
	
	@GetMapping("/admin/all")
	public ResponseEntity<List<UserResponseDTO>> getUsers(){
		return ResponseEntity.ok(userService.getAll());
	}
	
	  @DeleteMapping("/admin/{id}")
	  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
	    userService.deleteUser(id);
	    return ResponseEntity.noContent().build();
	  }
	  
	  @PatchMapping("/admin/{id}")
	  public ResponseEntity<UserResponseDTO> patchUser(@PathVariable Long id, @RequestBody @Valid UserRequestDTO dto) {
	    return ResponseEntity.ok(userService.updateUser(id, dto));
	  }
	  
	  @PutMapping("/admin/{id}")
	  public ResponseEntity<UserResponseDTO> updateTask(@PathVariable Long id, @RequestBody @Valid UserRequestDTO dto) {
	    return ResponseEntity.ok(userService.updateUser(id, dto));
	  }
	

}
