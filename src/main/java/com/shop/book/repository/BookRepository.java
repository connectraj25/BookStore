package com.shop.book.repository;

import com.shop.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    /**
     * Find books by author name
     * @param isbn
     * @return {@link Book}
     */
    Book findBookByIsbn(Long isbn);
}
