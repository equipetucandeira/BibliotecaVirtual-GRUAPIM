package br.ifsp.library.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.ifsp.library.dto.authentication.AuthenticationDTO;
import br.ifsp.library.exception.UnauthorizedException;
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

  public String authenticate(AuthenticationDTO dto) {
    User user = userRepository.findByEmail(dto.getEmail())
        .orElseThrow(() -> new UnauthorizedException("Credenciais inválidas"));

    if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
      throw new UnauthorizedException("Credenciais inválidas");
    }

    return jwtService.generateToken(user);
  }

}
