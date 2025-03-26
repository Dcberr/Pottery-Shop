package com.project.potteryshop.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.potteryshop.Dto.Request.Product.ProductCreateRequest;
import com.project.potteryshop.Dto.Response.Product.ProductResponse;
import com.project.potteryshop.Entity.Image;
import com.project.potteryshop.Entity.Product;
import com.project.potteryshop.Entity.ProductCategory;
import com.project.potteryshop.Entity.ProductItem;
import com.project.potteryshop.Mapper.ProductItemMapper;
import com.project.potteryshop.Mapper.ProductMapper;
import com.project.potteryshop.Repository.ImageRepository;
import com.project.potteryshop.Repository.ProductCategoryRepository;
import com.project.potteryshop.Repository.ProductItemRepository;
import com.project.potteryshop.Repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductMapper productMapper;
    private final ImageRepository imageRepository;
    private final ProductItemMapper productItemMapper;
    private final ProductItemRepository productItemRepository;

    @Transactional
    public Product createProduct(ProductCreateRequest request, String categoryId) {
        // Tìm category trước khi tạo product
        ProductCategory productCategory = productCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("ProductCategory not found"));

        // Khởi tạo và thiết lập Product
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setProductCategory(productCategory);
        product.setRemainingQuantity(request.getRemainingQuantity());

        // Lưu product để có product ID
        productRepository.save(product);

        // Tạo và liên kết Image
        Image image = new Image();
        image.setProductId(product.getProductId());
        product.setImage(image);
        imageRepository.save(image);

        // Tạo và liên kết ProductItem
        ProductItem productItem = productItemMapper.toProductItem(
                productItemMapper.toBodyProductItem(request));
        productItem.setProduct(product);
        productItemRepository.save(productItem);
        product.setProductItem(productItem);

        return product;
    }

    public Product getProductById(String productId) {
        return productRepository.findByProductId(productId);
    }
}