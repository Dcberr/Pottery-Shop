package com.project.potteryshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping
    public ApiResponse<Discount> getDiscountById(@RequestParam String discountId) {
        return ApiResponse.<Discount>builder()
                .code(200)
                .message("Get Discount " + discountId + " Successful!!!")
                .result(discountService.getDiscountById(discountId))
                .build();
    }
}
