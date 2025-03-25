package com.project.potteryshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.potteryshop.Entity.ProductItem;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, String> {
    // ProductItem getProductItemByProductId(String productId);
}
