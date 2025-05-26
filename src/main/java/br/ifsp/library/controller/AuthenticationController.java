package br.ifsp.library.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ifsp.library.model.User;
import br.ifsp.library.service.AuthenticationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class AuthenticationController {
	
	private final AuthenticationService authenticationService;
	
	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@PostMapping("/auth")
	public String authenticate(@RequestBody @Valid User user) {
		return authenticationService.authenticate(user);
	}
}
