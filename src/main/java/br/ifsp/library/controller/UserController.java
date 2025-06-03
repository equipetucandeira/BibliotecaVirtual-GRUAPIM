package br.ifsp.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ifsp.library.dto.authentication.UserRegistrationDTO;
import br.ifsp.library.model.User;
import br.ifsp.library.service.UserService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public User createUser(@RequestBody @Valid UserRegistrationDTO user) {
		return userService.createUser(user);
	}
	

}
