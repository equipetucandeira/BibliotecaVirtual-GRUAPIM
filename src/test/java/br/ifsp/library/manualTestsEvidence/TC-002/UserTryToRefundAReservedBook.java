import br.ifsp.library.exception.BadRequestException;
import br.ifsp.library.exception.ResourceNotFoundException;
import br.ifsp.library.model.Book;
import br.ifsp.library.model.Reservation;
import br.ifsp.library.model.User;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.ifsp.library.repository.BookRepository;
import br.ifsp.library.repository.ReservationRepository;
import br.ifsp.library.repository.UserRepository;
import br.ifsp.library.service.ReservationService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTryToRefundAReservedBook {
  @MockBean
  private ReservationRepository reservationRepository;

  @MockBean
  private BookRepository bookRepository;

  @MockBean
  private UserRepository userRepository;

  @Autowired
  private ReservationService reservationService;

  @Test
  void shouldCreateReservationSuccessfully() {
    Book book = new Book();
    book.setId(1L);
    book.setTitle("Teste de livro");
    book.setAuthor("Autor Anonimo");
    book.setDescription("Book's description");
    book.setQuantity(2);

    User user = new User();
    user.setName("teste");
    user.setEmail("teste@g.com");
    user.setPassword("12345");

    Reservation reservation = new Reservation();
    reservation.setId(1L);
    reservation.setBook(book);
    reservation.setUser(user);
    reservation.setActive(true);

    when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
    when(userRepository.findByName("teste")).thenReturn(Optional.of(user));
    when(reservationRepository.countActiveReservationsByBookId(1L)).thenReturn(0L);

    when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

    assertThrows(ResourceNotFoundException.class, () -> {
      reservationService.devolution(1L);
    });

    ArgumentCaptor<Reservation> captor = ArgumentCaptor.forClass(Reservation.class);
    verify(reservationRepository).save(any(Reservation.class));

    Reservation saved = captor.getValue();
    assertFalse(saved.isActive());
    assertNotNull(saved.getEndDate());
  }

}
