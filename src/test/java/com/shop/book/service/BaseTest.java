package com.shop.book.service;

import com.shop.book.entity.Book;
import com.shop.book.entity.Promotion;
import com.shop.book.repository.PromotionRepository;
import org.junit.Before;
import org.mockito.Mock;

import static org.mockito.Mockito.reset;

public abstract class BaseTest {
    protected final Long bookId1 = 100L, bookId2 = 200L, bookId3 = 300L;
    protected final String type1 = "fiction", type2 = "comic", type3 = "novel",
            author1 = "Author1", author2 = "Author2", author3 = "Author3",
            name1 = "Name1", name2 = "Name1", name3 = "Name1",
            description1 = "description1", description2 = "description2", description3 = "description3",
            promoCodeFiction = "PROMOFICTION", promoCodeComic = "PROMOCOMIC", promoCodeNovel = "PROMONOVEL",
            bookTypeFiction = "fiction", bookTypeComic = "comic", bookTypeNovel = "novel";
    protected Double price1 = 100.00, price2 = 200.00, price3 = 300.00;
    protected final Long isbn1 = 20012001L, isbn2 = 20022002L, isbn3 = 20032003L;

    @Mock
    protected Book mockBook1, mockBook2, mockBook3;
    @Mock
    protected Promotion fictionPromotion, allPromotion;


    @Before
    public void setUp() {
        reset(mockBook1, mockBook2, mockBook3, fictionPromotion, allPromotion);
    }

    protected void mockBooks() {
        mockBook1 = new Book();
        mockBook1.setId(bookId1);
        mockBook1.setName(name1);
        mockBook1.setDescription(description1);
        mockBook1.setAuthor(author1);
        mockBook1.setType(type1);
        mockBook1.setPrice(price1);
        mockBook1.setIsbn(isbn1);

        mockBook2 = new Book();
        mockBook2.setId(bookId2);
        mockBook2.setName(name2);
        mockBook2.setDescription(description2);
        mockBook2.setAuthor(author2);
        mockBook2.setType(type2);
        mockBook2.setPrice(price2);
        mockBook2.setIsbn(isbn2);

        mockBook3 = new Book();
        mockBook3.setId(bookId3);
        mockBook3.setName(name3);
        mockBook3.setDescription(description3);
        mockBook3.setAuthor(author3);
        mockBook3.setType(type3);
        mockBook3.setPrice(price3);
        mockBook3.setIsbn(isbn3);
    }

    protected void mockPromoFiction(Double percent) {
        fictionPromotion = new Promotion();
        fictionPromotion.setPromoCode(promoCodeFiction);
        fictionPromotion.setBookType(bookTypeFiction);
        fictionPromotion.setDiscountPercent(percent);
    }

    protected void mockPromoAll(Double percent) {
        allPromotion = new Promotion();
        allPromotion.setPromoCode(promoCodeFiction);
        allPromotion.setBookType(null);
        allPromotion.setDiscountPercent(percent);
    }
}