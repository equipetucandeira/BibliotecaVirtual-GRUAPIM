package br.ifsp.library.controller;

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
