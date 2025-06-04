package br.ifsp.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifsp.library.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  Optional<User> findByName(String name);

}
