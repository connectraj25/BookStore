package com.shop.book.service;

import com.shop.book.entity.Book;

import java.util.Collection;

public interface BookService {
    /**
     * Get all Books
     * @return {@link Collection<Book>}
     */
    Collection<Book> getBooks();

    /**
     * Create a new book
     * @param book
     * @return {@link Book}
     */
    Book createBook(Book book);

    /**
     * Update a new book
     * @param book
     * @return {@link Book}
     */
    Book updateBook(Book book);

    /**
     * delete the book by id has passed as an argument
     * @param id
     */
    void deleteBook(Long id);

    /**
     * calculate the discounts and return totalPrice for the checkout books
     * @param books
     * @param promoCode
     * @return
     */
    Double checkOut(Collection<Book> books, String promoCode);
}
