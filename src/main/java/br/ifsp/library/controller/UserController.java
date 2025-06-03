package br.ifsp.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

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
	
	@GetMapping("/all")
	public ResponseEntity<List<UserResponseDTO>> getUsers(){
		return ResponseEntity.ok(userService.getAll());
	}
	

}
