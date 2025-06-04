package br.ifsp.library.config;

import br.ifsp.library.model.RoleType;
import br.ifsp.library.model.User;
import br.ifsp.library.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class AdminInitializer {

  @Bean
  CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    return args -> {
      Optional<User> existingAdmin = userRepository.findByEmail("admin@ifsp.br");

      RoleType role = RoleType.ADMIN;

      if (existingAdmin.isEmpty()) {
        User admin = new User();
        admin.setName("Admin");
        admin.setEmail("admin@ifsp.br");
        admin.setPassword(passwordEncoder.encode("admin123")); // criptografa a senha
        admin.setRole(role); // ou ENUM se estiver usando enum

        userRepository.save(admin);
        System.out.println("Usuário admin criado: admin@ifsp.br / admin123");
      }
    };
  }
}
