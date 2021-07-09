package com.shop.book.repository;

import com.shop.book.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Promotion findByPromoCode(String promoCode);
}
