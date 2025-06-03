package br.ifsp.library.dto;

import br.ifsp.library.model.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BookResponseDTO {

  private Long id;
  private String title;
  private String description;
  private String author;
  private Integer quantity;

  public BookResponseDTO(Book book) {
	    this.id = book.getId();
	    this.title = book.getTitle();
	    this.description = book.getDescription();
      this.author = book.getAuthor();
      this.quantity = book.getQuantity();
	}
}
