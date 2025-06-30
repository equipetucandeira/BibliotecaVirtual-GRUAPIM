package br.ifsp.library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.ifsp.library.model.*;

public interface BookRepository extends JpaRepository<Book, Long> {
  Page<Book> findAll(Pageable pageable);

  Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

  Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);
}
