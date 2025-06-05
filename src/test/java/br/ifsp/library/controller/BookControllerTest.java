/*package br.ifsp.library.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import br.ifsp.library.model.Book;
import br.ifsp.library.dto.ReservationResponseDTO;
import br.ifsp.library.service.BookService;
import br.ifsp.library.service.ReservationService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
public class BookControllerTest {

  @MockBean
  private BookService bookService;

  @MockBean
  private ReservationService reservationService;

  @Autowired
  private MockMvc mockMvc;

  @WithMockUser(username = "Admin", roles = {"ADMIN"})
  @Test
  void shouldReserveBookSuccessfully() throws Exception {
    Book book = new Book();
    book.setId(1L);
    book.setTitle("Teste de livro");
    book.setAuthor("Autor Anônimo");
    book.setDescription("Descrição do livro");
    book.setQuantity(2);

    LocalDate startDate = LocalDate.now();
    LocalDate endDate = startDate.plusDays(7);

    ReservationResponseDTO responseDTO = new ReservationResponseDTO(1L, book, startDate, endDate, true);

    when(reservationService.reservBook(1L, "Admin"))
        .thenReturn(responseDTO);

    mockMvc.perform(post("/api/books/1/reserve")
            .accept("application/json"))
        .andExpect(status().isOk());
  }
}

*/
