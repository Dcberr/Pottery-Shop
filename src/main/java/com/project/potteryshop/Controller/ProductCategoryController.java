package com.project.potteryshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.potteryshop.Dto.ApiResponse;
import com.project.potteryshop.Dto.Response.ProductCategory.ProductCategoryResponse;
import com.project.potteryshop.Service.ProductCategoryService;

@RestController
@RequestMapping("/categories")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping
    public ApiResponse<ProductCategoryResponse> createCategory(@RequestParam String categoryName) {
        return ApiResponse.<ProductCategoryResponse>builder()
                .code(200)
                .message("Created Category Successful!!!")
                .result(productCategoryService.createCategory(categoryName))
                .build();
    }

    @GetMapping
    public ApiResponse<ProductCategoryResponse> getCategory(@RequestParam String categoryId) {
        return ApiResponse.<ProductCategoryResponse>builder()
                .code(200)
                .message("Get Category Successful!!!")
                .result(productCategoryService.getCategory(categoryId))
                .build();
    }
}
