package com.shop.book.controller;

import com.shop.book.entity.Book;
import com.shop.book.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class Controller {
    private final Logger log = LoggerFactory.getLogger(Controller.class);
    private final String rootURI = "/api";

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<Collection<Book>> getBooks() {
        log.info("Invoked - {}/books", rootURI);
        return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
    }

    @PostMapping(value = "/createbook")
    public ResponseEntity<Book> saveBook(@Valid @RequestBody Book book) {
        log.info("Invoked - {}/createbook", rootURI);
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    @PutMapping(value = "/updatebook")
    public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book) {
        log.info("Invoked - {}/updatebook", rootURI);
        return new ResponseEntity<>(bookService.updateBook(book), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deletebook/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Long id) {
        log.info("Invoked - {}/deletebook/{}", rootURI, id);
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(value = {"/checkout", "/checkout/{promoCode}"})
    public ResponseEntity<Double> getPriceAfterDiscount(@Valid @RequestBody Collection<Book> books,
                                                        @PathVariable(required = false) String promoCode) {
        log.info("Invoked - {}/checkout", rootURI);
        return new ResponseEntity<>(bookService.checkOut(books, promoCode), HttpStatus.OK);
    }
}
