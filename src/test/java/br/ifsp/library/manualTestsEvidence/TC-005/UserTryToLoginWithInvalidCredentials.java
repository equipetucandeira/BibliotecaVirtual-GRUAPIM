import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.*;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;


import static org.junit.jupiter.api.Assertions.*;

import br.ifsp.library.dto.authentication.AuthenticationDTO;
import br.ifsp.library.exception.UnauthorizedException;
import br.ifsp.library.model.User;
import br.ifsp.library.repository.UserRepository;
import br.ifsp.library.service.AuthenticationService;

public class UserTryToLoginWithInvalidCredentials {
  @MockBean
  UserRepository userRepository;

  @Autowired
  AuthenticationService authenticationService;

  @Test
  void ShouldReturnBadCredentialsWhenPasswordIsIncorrect() {
    AuthenticationDTO dto = new AuthenticationDTO();
    dto.setEmail("teste@g.com");
    dto.setPassword("senhaErrada");

    User user = new User();
    user.setEmail("teste@g.com");
    user.setPassword(new BCryptPasswordEncoder().encode("12345"));

    when(userRepository.findByEmail("teste@g.com"))
        .thenReturn(Optional.of(user));

    assertThrows(UnauthorizedException.class,
        () -> authenticationService.authenticate(dto));

  }

}

