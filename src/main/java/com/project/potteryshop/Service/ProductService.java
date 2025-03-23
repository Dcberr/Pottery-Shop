package com.project.potteryshop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.potteryshop.Dto.Request.Product.ProductCreateRequest;
import com.project.potteryshop.Dto.Response.Product.ProductResponse;
import com.project.potteryshop.Entity.Image;
import com.project.potteryshop.Entity.Product;
import com.project.potteryshop.Entity.ProductCategory;
import com.project.potteryshop.Mapper.ProductMapper;
import com.project.potteryshop.Repository.ImageRepository;
import com.project.potteryshop.Repository.ProductCategoryRepository;
import com.project.potteryshop.Repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ImageRepository imageRepository;

    public ProductResponse createProduct(ProductCreateRequest request, String categoryId) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        ProductCategory productCategory = productCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("ProductCategory not found"));
        product.setProductCategory(productCategory);

        Image image = new Image();
        imageRepository.save(image);
        product.setImage(image);

        productRepository.save(product);

        return productMapper.toProductResponse(product);
    }
}
