package com.project.potteryshop.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.potteryshop.Entity.Product;
import com.project.potteryshop.Entity.ProductCategory;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByProductCategory(ProductCategory category);
}
