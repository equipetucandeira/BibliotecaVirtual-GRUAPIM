package br.ifsp.library.dto;

import br.ifsp.library.model.Book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {
	@NotBlank(message = "Title is required")
	private String title;
	@NotBlank(message = "Description is required")
	private String description;
	@NotBlank(message = "Author is required")
	private String author;
	@NotNull(message = "Quantity is required")
	private Integer quantity;
	
	
	public Book transformToObj() {
		return new Book(title, description, author, quantity);
	}

	
}
