package com.project.potteryshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ApiResponse<CartResponse> createCart(Cart cart) {
        return ApiResponse.<CartResponse>builder()
                .code(200)
                .message("Created Cart Successfull!!!")
                .result(cartService.createCart(cart))
                .build();
    }
}
