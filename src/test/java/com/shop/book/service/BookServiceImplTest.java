package com.shop.book.service;

import com.shop.book.entity.Book;
import com.shop.book.entity.Promotion;
import com.shop.book.exception.ResourceAlreadyExistsException;
import com.shop.book.exception.ResourceNotFoundException;
import com.shop.book.repository.BookRepository;
import com.shop.book.repository.PromotionRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest extends BaseTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private PromotionRepository promotionRepository;

    @InjectMocks
    private BookServiceImpl service;

    @Before
    public void setUp() {
        reset(bookRepository, promotionRepository);
        mockBooks();
    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getBooks() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(mockBook1, mockBook2, mockBook3));
        assertThat("Verify Getting list of all books",
                service.getBooks(),
                containsInAnyOrder(
                        allOf(hasProperty("id", is(bookId1)),
                                hasProperty("name", is(name1)),
                                hasProperty("description", is(description1)),
                                hasProperty("author", is(author1)),
                                hasProperty("type", is(type1)),
                                hasProperty("price", is(price1)),
                                hasProperty("isbn", is(isbn1))),
                        allOf(hasProperty("id", is(bookId2)),
                                hasProperty("name", is(name2)),
                                hasProperty("description", is(description2)),
                                hasProperty("author", is(author2)),
                                hasProperty("type", is(type2)),
                                hasProperty("price", is(price2)),
                                hasProperty("isbn", is(isbn2))),
                        allOf(hasProperty("id", is(bookId3)),
                                hasProperty("name", is(name3)),
                                hasProperty("description", is(description3)),
                                hasProperty("author", is(author3)),
                                hasProperty("type", is(type3)),
                                hasProperty("price", is(price3)),
                                hasProperty("isbn", is(isbn3)))));
    }

    @Test
    public void testCreateBook() {
        Book newBook = new Book("name4", "description4", "author4", "type4", 15.77, 123456789L);
        when(bookRepository.findBookByIsbn(newBook.getIsbn())).thenReturn(null);
        when(bookRepository.save(newBook)).thenReturn(mockBook1);
        assertThat("Verify that new book is added",
                service.createBook(newBook),
                allOf(hasProperty("id", is(bookId1)),
                        hasProperty("author", is(author1))));
    }

    @Test
    public void testResourceAlreadyExistsExceptionWhenInvokeCreateBook() {
        Book newBook = new Book(name1, description1, author1, type1, price1, isbn1);
        when(bookRepository.findBookByIsbn(newBook.getIsbn())).thenReturn(mockBook1);
        expectedException.expect(ResourceAlreadyExistsException.class);
        expectedException.expectMessage("Resource Already Exists :" + mockBook1);
        service.createBook(newBook);
    }

    @Test
    public void testUpdateBook() {
        when(bookRepository.findById(bookId1)).thenReturn(Optional.ofNullable(mockBook1));
        when(bookRepository.save(mockBook1)).thenReturn(mockBook1);
        assertThat("Verify that book is updated",
                service.updateBook(mockBook1),
                allOf(hasProperty("id", is(bookId1)),
                        hasProperty("author", is(author1))));
    }

    @Test
    public void testResourceNotFoundWhenInvokeUpdateBook() {
        when(bookRepository.findById(mockBook1.getId())).thenReturn(Optional.empty());
        expectedResourceNotFoundException("Book not found with id : " + mockBook1.getId());
        service.updateBook(mockBook1);
    }

    @Test
    public void testDeleteBook() {
        when(bookRepository.findById(bookId1)).thenReturn(Optional.ofNullable(mockBook1));
        service.deleteBook(bookId1);
    }

    @Test
    public void testResourceNotFoundWhenInvokeDeleteBook() {
        when(bookRepository.findById(bookId1)).thenReturn(Optional.empty());
        expectedResourceNotFoundException("Book not found with id : " + bookId1);
        service.deleteBook(bookId1);
    }

    private void expectedResourceNotFoundException(String message) {
        expectedException.expect(ResourceNotFoundException.class);
        expectedException.expectMessage(message);
    }

    @Test
    public void testCheckoutABookWithPromoCodeForFiction() {
        mockPromoFiction(10.00);
        String promoCode = "PROMOFICTION";
        when(promotionRepository.findByPromoCode(promoCode)).thenReturn(fictionPromotion);

        Double expectedPrice = 90.00;
        Double priceAfterDiscount = service.checkOut(Collections.singleton(mockBook1), promoCode);
        assertEquals(expectedPrice, priceAfterDiscount);
    }

    @Test
    public void testCheckoutBooksWithPromoCodeForFictionAndComic() {
        mockPromoFiction(10.00);
        mockAndVerifyAterDiscountPrice("PROMOFICTION", fictionPromotion, 290.00);
    }

    @Test
    public void testCheckoutBooksWithPromoCodeForAllTypes() {
        mockPromoAll(5.00);
        mockAndVerifyAterDiscountPrice("PROMOALL", allPromotion, 285.00);
    }

    private void mockAndVerifyAterDiscountPrice(String promoCode, Promotion promotion, Double expectedPrice) {
        when(promotionRepository.findByPromoCode(promoCode)).thenReturn(promotion);

        Double priceAfterDiscount = service.checkOut(Arrays.asList(mockBook1, mockBook2), promoCode);
        assertEquals(expectedPrice, priceAfterDiscount);
    }
}