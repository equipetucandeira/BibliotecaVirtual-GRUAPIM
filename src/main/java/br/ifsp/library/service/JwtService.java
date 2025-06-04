package br.ifsp.library.service;

import java.time.Instant;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.util.List;

import br.ifsp.library.model.User;
@Service
public class JwtService {

	private final JwtEncoder jwtEncoder;

	public JwtService(JwtEncoder encoder) {
		this.jwtEncoder = encoder;
	}

public String generateToken(User user) {
    Instant now = Instant.now();
    long expire = 3600L;

    var claims = JwtClaimsSet.builder()
        .issuer("spring-security")
        .issuedAt(now)
        .expiresAt(now.plusSeconds(expire))
        .subject(user.getName())
        .claim("userId", user.getId())
        .claim("roles", List.of(user.getRole().name())) // ADICIONA A ROLE
        .build();

    return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
}

}
