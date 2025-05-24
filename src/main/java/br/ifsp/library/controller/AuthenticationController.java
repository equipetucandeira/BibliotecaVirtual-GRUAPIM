package br.ifsp.library.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ifsp.library.service.AuthenticationService;

@RestController
public class AuthenticationController {
	
	private final AuthenticationService authenticationService;
	
	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@PostMapping("/authenticate")
	public String authenticate() {
		return authenticationService.authenticate();
	}
}
