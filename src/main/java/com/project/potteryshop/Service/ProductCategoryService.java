package com.project.potteryshop.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.project.potteryshop.Dto.Response.ProductCategory.ProductCategoryResponse;
import com.project.potteryshop.Entity.ProductCategory;
import com.project.potteryshop.Mapper.ProductCategoryMapper;
import com.project.potteryshop.Mapper.ProductMapper;
import com.project.potteryshop.Repository.ProductCategoryRepository;
import com.project.potteryshop.Repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    private ProductMapper productMapper;

    @PreAuthorize("hasAuthority('CREATE_CATEGORY')")
    public ProductCategoryResponse createCategory(String categoryName) {
        ProductCategory newCategory = new ProductCategory();
        newCategory.setName(categoryName);

        // List<Product> productsOfCategory =
        // productCategoryRepository.findByCategory(newCategory);
        newCategory.setNumOfProduct(0);
        // newCategory.setProducts(productsOfCategory);

        productCategoryRepository.save(newCategory);

        ProductCategoryResponse response = productCategoryMapper.toProductCategoryResponse(newCategory);
        if (newCategory.getNumOfProduct() != 0) {
            response.setProducts(newCategory.getProducts()
                    .stream()
                    .map(productMapper::toProductResponse) // Chuyển từng Product thành ProductResponse
                    .toList()); // Chuyển thành danh sách List<ProductResponse>
        }
        return response;

    }

    @PreAuthorize("hasAuthority('GET_CATEGORY')")
    public ProductCategoryResponse getCategory(String categoryId) {
        ProductCategory category = productCategoryRepository.getProductCategoryByCategoryId(categoryId);
        category.setNumOfProduct(productRepository.findByProductCategory(category).size());
        ProductCategoryResponse response = productCategoryMapper.toProductCategoryResponse(category);
        response.setProducts(category.getProducts()
                .stream()
                .map(productMapper::toProductResponse) // Chuyển từng Product thành ProductResponse
                .toList()); // Chuyển thành danh sách List<ProductResponse>
        productCategoryRepository.save(category);
        return response;
    }

    @PreAuthorize("hasAuthority('GET_ALL_CATEGORY')")
    public List<ProductCategory> getAllCategories() {
        return productCategoryRepository.findAll().stream().toList();
    }
}
