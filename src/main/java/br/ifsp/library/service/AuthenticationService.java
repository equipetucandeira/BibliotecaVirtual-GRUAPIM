package br.ifsp.library.service;

import org.springframework.stereotype.Service;

import br.ifsp.library.model.User;

@Service
public class AuthenticationService {

	private final JwtService jwtService;
	
	public AuthenticationService(JwtService jwtService) {
		this.jwtService = jwtService;
	}
	
	
	
	public String authenticate(User user) {
		return jwtService.generateToken(user);
	}
}
