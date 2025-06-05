package br.ifsp.library.dto;

import java.time.LocalDate;

import br.ifsp.library.model.Book;
import br.ifsp.library.model.Reservation;
import lombok.Data;
@Data
public class ReservationResponseDTO {
	private Long id;
	private Book book;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean active;
	
	public ReservationResponseDTO(Book book, LocalDate startDate, LocalDate endDate, boolean active) {
	    this.book = book;
	    this.startDate = startDate;
	    this.endDate = endDate;
	    this.active = active;
	}

	
	public ReservationResponseDTO(Long id, Book book, LocalDate startDate, LocalDate endDate, boolean active) {
		super();
		this.id = id;
		this.book = book;
		this.startDate = startDate;
		this.endDate = endDate;
		this.active = active;
	}
	
	public ReservationResponseDTO(Reservation res) {
		this.id = res.getId();
		this.book = res.getBook();
		this.startDate = res.getStartDate();
		this.endDate = res.getEndDate();
		this.active = res.isActive();
	}
}
