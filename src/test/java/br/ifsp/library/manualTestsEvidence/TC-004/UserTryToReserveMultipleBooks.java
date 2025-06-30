import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

import br.ifsp.library.model.Book;
import br.ifsp.library.repository.BookRepository;
import br.ifsp.library.repository.ReservationRepository;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTryToReserveMultipleBooks {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  BookRepository bookRepository;

  @Autowired
  ReservationRepository reservationRepository;

  @BeforeEach
  void seedBooks() {
    bookRepository.deleteAll();

    for (int i = 1; i <= 10; i++) {
      Book b = new Book(
          "Livro " + i,
          "Descrição " + i,
          "Autor " + i,
          1);
      bookRepository.save(b);
    }
  }

  @Test
  @WithMockUser(username = "admin")
  void shouldReserveMultipleBooksSequentially() throws Exception {

    List<Book> catalog = bookRepository.findAll();
    for (Book book : catalog) {
      mockMvc.perform(post("/" + book.getId() + "/reserve")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());
    }

    // Verifica se deu bom com as 10 reservas
    assertThat(reservationRepository.count()).isEqualTo(catalog.size());
  }

}
