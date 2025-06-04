package br.ifsp.library.dto;

import java.time.LocalDate;

import br.ifsp.library.model.Book;

public class ReservationResponseDTO {
	private Book book;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean active;
	
	public ReservationResponseDTO(Book book, LocalDate startDate, LocalDate endDate, boolean active) {
		super();
		this.book = book;
		this.startDate = startDate;
		this.endDate = endDate;
		this.active = active;
	}
}
