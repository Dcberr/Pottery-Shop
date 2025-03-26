package com.project.potteryshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.potteryshop.Dto.ApiResponse;
import com.project.potteryshop.Dto.Request.Product.ProductCreateRequest;
import com.project.potteryshop.Dto.Response.Product.ProductResponse;
import com.project.potteryshop.Entity.Product;
import com.project.potteryshop.Service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ApiResponse<Product> createProduct(@RequestBody ProductCreateRequest request,
            @RequestParam String categoryId) {
        return ApiResponse.<Product>builder()
                .code(200)
                .message("Created Product Successful!!!")
                .result(productService.createProduct(request, categoryId))
                .build();
    }

    @GetMapping("/{productId}")
    public ApiResponse<Product> getProductById(@PathVariable String productId) {
        return ApiResponse.<Product>builder()
                .code(200)
                .message("Get Product " + productId + " Successfull!!!")
                .result(productService.getProductById(productId))
                .build();
    }
}
