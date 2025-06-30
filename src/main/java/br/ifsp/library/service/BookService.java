package br.ifsp.library.service;

import br.ifsp.library.model.Book;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageImpl;

import br.ifsp.library.repository.BookRepository;
import br.ifsp.library.repository.ReservationRepository;
//import io.swagger.v3.oas.models.examples.Example;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import static org.springframework.data.domain.ExampleMatcher.StringMatcher.CONTAINING;


import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import br.ifsp.library.dto.BookResponseDTO;
import br.ifsp.library.dto.BookFilterDto;
import br.ifsp.library.dto.BookRequestDTO;
import br.ifsp.library.exception.ResourceNotFoundException;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private ReservationRepository reservationRepository;

  @Autowired
  private ModelMapper model;

  private BookResponseDTO toDtoWithAvailability(Book book) {
    long activeReservations = reservationRepository.countActiveReservationsByBookId(book.getId());
    return new BookResponseDTO(book, activeReservations);
  }

  public Page<BookResponseDTO> getAllBooks(int page, int size, String sortBy) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
    Page<Book> books = bookRepository.findAll(pageable);
    if (books == null)
      return Page.empty(pageable);
    return books.map(this::toDtoWithAvailability);
  }

  public Page<BookResponseDTO> getAvailableBooks(int page, int size, String sortBy) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    Page<Book> allBooks = bookRepository.findAll(pageable);

    List<BookResponseDTO> availableBooks = allBooks.stream()
        .map(book -> {
          long activeReservations = reservationRepository.countActiveReservationsByBookId(book.getId());
          return new BookResponseDTO(book, activeReservations);
        })
        .filter(dto -> dto.getAvailableQuantity() > 0)
        .toList();

    return new PageImpl<>(availableBooks, pageable, availableBooks.size());
  }

  public BookResponseDTO getBookById(Long id) {
    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));
    return toDtoWithAvailability(book);
  }

  public Page<BookResponseDTO> getBooksByAuthor(int page, int size, String sortBy, String author) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
    Page<Book> books = bookRepository.findByAuthorContainingIgnoreCase(author, pageable);
    if (books == null)
      return Page.empty(pageable);
    return books.map(this::toDtoWithAvailability);
  }

  public Page<BookResponseDTO> getBooksByTitle(int page, int size, String sortBy, String title) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
    Page<Book> books = bookRepository.findByTitleContainingIgnoreCase(title, pageable);
    if (books == null) {
      return Page.empty(pageable);
    }
    return books.map(this::toDtoWithAvailability);
  }

  public BookResponseDTO createBook(BookRequestDTO dto) {
    // if (dto.getLimitDate().isBefore(LocalDate.now())) {
    // throw new BadRequestException("A data limite não pode ser anterior à data
    // atual.");
    // }
    Book book = model.map(dto, Book.class);
    bookRepository.save(book);
    return toDtoWithAvailability(book);
  }

  public boolean deleteBook(Long id) {
    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));
    bookRepository.delete(book);
    return true;
  }

  public BookResponseDTO updateBook(Long id, BookRequestDTO dto) {
    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));

    model.map(dto, book);

    Book updated = bookRepository.save(book);
    return toDtoWithAvailability(updated);
  }

  public Page<BookResponseDTO> filterBooks(BookFilterDto dto,
      int page, int size, String sortBy) {

    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

    Book book = new Book();
    book.setTitle(dto.getTitle());
    book.setAuthor(dto.getAuthor());

    ExampleMatcher matcher = ExampleMatcher.matchingAll()
        .withIgnoreNullValues() // ignora campos não enviados
        .withStringMatcher(StringMatcher.CONTAINING) // LIKE %valor%
        .withIgnoreCase();

    Example<Book> example = Example.of(book, matcher);

    return bookRepository.findAll(example, pageable)
        .map(this::toDtoWithAvailability);
  }

}
