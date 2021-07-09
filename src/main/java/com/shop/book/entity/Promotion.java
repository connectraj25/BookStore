package com.shop.book.entity;

import javax.persistence.*;

@Entity
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "promocode", nullable = false)
    private String promoCode;
    @Column(name = "booktype")
    private String bookType;
    @Column(name = "discountprice")
    private Double discountPercent;

    public Promotion() {
    }

    public Promotion(String promoCode, String bookType, Double discountPercent) {
        this.promoCode = promoCode;
        this.bookType = bookType;
        this.discountPercent = discountPercent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "id=" + id +
                ", promoCode='" + promoCode + '\'' +
                ", bookType='" + bookType + '\'' +
                ", discountPercent=" + discountPercent +
                '}';
    }
}
