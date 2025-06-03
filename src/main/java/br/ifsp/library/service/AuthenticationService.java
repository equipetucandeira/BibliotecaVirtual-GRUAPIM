package br.ifsp.library.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.ifsp.library.dto.authentication.AuthenticationDTO;
import br.ifsp.library.model.User;
import br.ifsp.library.repository.UserRepository;

@Service
public class AuthenticationService {
	private UserRepository userRepository;	
	private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
	
    public AuthenticationService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }
	
	
	public String authenticate(AuthenticationDTO authenticationDto) {
		User myUser = userRepository.findByEmail(authenticationDto.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
		if(!passwordEncoder.matches(authenticationDto.getPassword(), myUser.getPassword())) {
            throw new RuntimeException("Invalid credentials");
		}
		return jwtService.generateToken(myUser);
		
	}
}
