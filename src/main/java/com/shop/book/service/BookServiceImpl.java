package com.shop.book.service;

import com.shop.book.entity.Book;
import com.shop.book.entity.Promotion;
import com.shop.book.exception.ResourceAlreadyExistsException;
import com.shop.book.exception.ResourceNotFoundException;
import com.shop.book.repository.BookRepository;
import com.shop.book.repository.PromotionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public Collection<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book createBook(Book book) {
        Optional<Book> bookFromDb = Optional.ofNullable(this.bookRepository.findBookByIsbn(book.getIsbn()));
        if (!bookFromDb.isPresent()) {
            log.info("Saving {}", book);
            return bookRepository.save(book);
        } else {
            throw new ResourceAlreadyExistsException("Resource Already Exists :" + bookFromDb.get());
        }
    }

    @Override
    public Book updateBook(Book book) {
        Optional<Book> bookFromDb = this.bookRepository.findById(book.getId());
        if (bookFromDb.isPresent()) {
            Book updatedBook = bookFromDb.get();
            BeanUtils.copyProperties(book, updatedBook);
            log.info("Updating {}", updatedBook);
            return bookRepository.save(updatedBook);
        } else {
            throw new ResourceNotFoundException("Book not found with id : " + book.getId());
        }
    }

    @Override
    public void deleteBook(Long id) {
        Optional<Book> bookFromDb = this.bookRepository.findById(id);
        if (bookFromDb.isPresent()) {
            Book deletedBook = bookFromDb.get();
            log.info("Deleting {}", deletedBook);
            bookRepository.delete(deletedBook);
        } else {
            throw new ResourceNotFoundException("Book not found with id : " + id);
        }
    }

    @Override
    public Double checkOut(Collection<Book> books, String promoCode) {
        double totalPrice = 0.00;
        String promotionBookType = "";
        Double discountPercentage = 0.00;
        Optional<Promotion> promotionFromDb = Optional.ofNullable(promotionRepository.findByPromoCode(promoCode));
        if(promotionFromDb.isPresent()) {
            Promotion promotion = promotionFromDb.get();
            promotionBookType = promotion.getBookType();
            discountPercentage = promotion.getDiscountPercent();
        }
        for (Book book : books) {
            totalPrice += calculateDiscountByBook(book, promotionBookType, discountPercentage);
        }
        return totalPrice;
    }

    private Double calculateDiscountByBook(Book book, String promotionBookType, Double discountPercentage) {
        if(discountPercentage > 0) {
            if (book.getType().equals(promotionBookType)) {
                return book.getPrice() - (book.getPrice() * (discountPercentage / 100));
            } else if (!StringUtils.hasText(promotionBookType)) {
                return book.getPrice() - (book.getPrice() * (discountPercentage / 100));
            }
        }
        return book.getPrice() != null ? book.getPrice() : 0.00;
    }
}
