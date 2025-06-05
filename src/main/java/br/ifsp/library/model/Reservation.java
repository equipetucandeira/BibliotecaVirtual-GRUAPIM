package br.ifsp.library.model;


import java.time.LocalDate;

import br.ifsp.library.dto.ReservationResponseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
public class Reservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  private Book book;
  @ManyToOne
  private User user;
  private LocalDate startDate;
  private LocalDate endDate;
  private boolean active;

  public Reservation() {

  }

  public Reservation(Book book, User user, LocalDate startDate, LocalDate endDate, boolean active) {
    this.book = book;
    this.user = user;
    this.startDate = startDate;
    this.endDate = startDate.plusDays(7);
    this.active = true;
  }
  
  public ReservationResponseDTO transformResponseDTO() {
	  ReservationResponseDTO dto = new ReservationResponseDTO(this.id, this.book, this.startDate, this.endDate, this.active);
	  return dto;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
