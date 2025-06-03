package br.ifsp.library.model;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank(message = "Title is required")
  private String title;
  @NotBlank(message = "Description is required")
  private String description;
  @NotBlank(message = "Author is required")
  private String author;
  @NotBlank(message = "Quantity is required")
  private Integer quantity;
  @OneToMany(mappedBy = "book")
  private ArrayList<Reservation> reservations;

  public Book() {

  }

  public Book(String title, String description, String author, Integer quantity) {
    this.title = title;
    this.description = description;
    this.author = author;
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public boolean isReserved() {
    return reservations.stream().anyMatch(Reservation::isActive);
  }

}
