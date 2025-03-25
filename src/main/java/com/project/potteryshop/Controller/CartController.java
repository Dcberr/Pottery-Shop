package com.project.potteryshop.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.potteryshop.Dto.ApiResponse;
import com.project.potteryshop.Dto.Response.Cart.CartResponse;
import com.project.potteryshop.Entity.Cart;
import com.project.potteryshop.Service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping
    public ApiResponse<Cart> createCart(@RequestBody Cart cart) {
        return ApiResponse.<Cart>builder()
                .code(200)
                .message("Created Cart Successfull!!!")
                .result(cartService.createCart())
                .build();
    }

    @GetMapping
    public ApiResponse<List<CartResponse>> getAllCart() {
        return ApiResponse.<List<CartResponse>>builder()
                .code(200)
                .message("Get All Carts Successful!!!")
                .result(cartService.getAllCart())
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<Cart> addNewProduct(@RequestParam String userId, @RequestParam String productId) {
        return ApiResponse.<Cart>builder()
                .code(200)
                .message("Add Product " + productId + " Successful!!!")
                .result(cartService.addNewProduct(userId, productId))
                .build();
    }
}
