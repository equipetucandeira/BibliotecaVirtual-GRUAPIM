package br.ifsp.library.dto;

import br.ifsp.library.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibUseDTO {
	private Long totalLoans;
	private Long totalUsers;
	private MostBorrowedDTO mostLoans;
}
