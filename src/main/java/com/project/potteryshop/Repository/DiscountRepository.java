package com.project.potteryshop.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.potteryshop.Entity.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, String> {
    Discount findByDiscountId(String discountId);

    @Query("SELECT d FROM Discount d JOIN d.products p " +
            "WHERE p.productId = :productId " +
            "AND d.startDate <= CURRENT_DATE " +
            "AND d.endDate >= CURRENT_DATE")
    List<Discount> findAllDiscountByProductId(String productId);
}
