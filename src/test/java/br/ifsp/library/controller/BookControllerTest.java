package br.ifsp.library.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import br.ifsp.library.model.User;
import br.ifsp.library.model.Book;
import br.ifsp.library.dto.ReservationResponseDTO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import br.ifsp.library.controller.BookController;
import br.ifsp.library.service.BookService;
import br.ifsp.library.service.ReservationService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(BookController.class)
public class BookControllerTest {

  @MockBean
  private BookService bookService;

  @MockBean
  private ReservationService reservationService;

  @Autowired
  private MockMvc mockMvc;

  @WithMockUser(username = "admin", roles = "ADMIN")
  @Test
  void shouldReserveBookSuccessfully() throws Exception {
    // Dados simulados
    Book book = new Book();
    book.setId(1L);
    book.setTitle("Teste de livro");
    book.setAuthor("Autor Anônimo");
    book.setDescription("Descrição do livro");
    book.setQuantity(2);

    LocalDate startDate = LocalDate.now();
    LocalDate endDate = startDate.plusDays(7);

    ReservationResponseDTO responseDTO = new ReservationResponseDTO(book, startDate, endDate, true);

    // Mockando o service
    Mockito.when(reservationService.reservBook(1L, "admin"))
        .thenReturn(responseDTO);

    // Executando o teste
    mockMvc.perform(post("/api/books/1/reserve"))
        .andExpect(status().isOk());
  }

}
