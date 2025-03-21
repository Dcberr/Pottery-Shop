package com.project.potteryshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.potteryshop.Dto.ApiResponse;
import com.project.potteryshop.Dto.Request.Product.ProductCreateRequest;
import com.project.potteryshop.Dto.Response.Product.ProductResponse;
import com.project.potteryshop.Service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ApiResponse<ProductResponse> createProduct(@RequestBody ProductCreateRequest request,
            @RequestParam String categoryId) {
        return ApiResponse.<ProductResponse>builder()
                .code(200)
                .message("Created Product Successful!!!")
                .result(productService.createProduct(request, categoryId))
                .build();
    }
}
