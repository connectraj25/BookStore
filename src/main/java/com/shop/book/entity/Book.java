package com.shop.book.entity;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "type")
    private String type;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "isbn", nullable = false)
    private Long isbn;

    public Book() {
    }

    public Book(String name, String description, String author, String type, Double price, Long isbn) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.type = type;
        this.price = price;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name=" + name +
                ", description=" + description +
                ", author=" + author +
                ", type=" + type +
                ", price=" + price +
                ", isbn=" + isbn +
                '}';
    }
}
