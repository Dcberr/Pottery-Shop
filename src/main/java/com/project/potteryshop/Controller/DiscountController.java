package com.project.potteryshop.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.potteryshop.Dto.ApiResponse;
import com.project.potteryshop.Dto.Request.Discount.DiscountCreateRequest;
import com.project.potteryshop.Entity.Discount;
import com.project.potteryshop.Service.DiscountService;

@RestController
@RequestMapping("/discounts")
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @PostMapping
    public ApiResponse<Discount> createDiscount(@RequestBody DiscountCreateRequest request) {
        return ApiResponse.<Discount>builder()
                .code(200)
                .message("Create Discount Successful!!!")
                .result(discountService.createDiscount(request))
                .build();
    }

    @GetMapping("/{discountId}")
    public ApiResponse<Discount> getDiscountById(@PathVariable String discountId) {
        return ApiResponse.<Discount>builder()
                .code(200)
                .message("Get Discount " + discountId + " Successful!!!")
                .result(discountService.getDiscountById(discountId))
                .build();
    }

    @GetMapping
    public ApiResponse<List<Discount>> getAllDiscounts() {
        return ApiResponse.<List<Discount>>builder()
                .code(200)
                .message("Get All Discount Successful!!!")
                .result(discountService.getAllDiscounts())
                .build();
    }
}
