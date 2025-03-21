package com.project.potteryshop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.potteryshop.Dto.Response.ProductCategory.ProductCategoryResponse;
import com.project.potteryshop.Entity.Product;
import com.project.potteryshop.Entity.ProductCategory;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductCategoryService {
    @Autowired
    private ProductService productService;

    public ProductCategory createCategory(String categoryName) {
        ProductCategory newCategory = new ProductCategory();

        newCategory.setName(categoryName);

        newCategory.setNumOfProduct(0);

        return newCategory;

    }
}
