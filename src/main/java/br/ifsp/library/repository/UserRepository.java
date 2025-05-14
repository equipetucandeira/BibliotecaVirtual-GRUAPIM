package br.ifsp.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifsp.library.model.User;
public interface UserRepository extends JpaRepository<User, Long> {

}
