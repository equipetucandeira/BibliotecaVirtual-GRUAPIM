/*package br.ifsp.library.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.ifsp.library.controller.BookController;
import br.ifsp.library.service.BookService;

@WebMvcTest(BookController.class)
public class BookControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BookService bookService;

  @Test
  @WithMockUser(username = "teste@g.com", roles = "DEFAULT")
  void shouldReserveBookSuccessfully() throws Exception {
    mockMvc.perform(post("/api/books/1/reserve"))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(username = "thiago", roles = "USER")
  void shouldReturnUnauthorizedWhenUserIsNotAuthenticated() throws Exception {
    mockMvc.perform(post("/api/books/1/reserve"))
        .andExpect(status().isUnauthorized());
  }

}
*/
