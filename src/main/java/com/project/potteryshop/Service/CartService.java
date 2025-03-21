package com.project.potteryshop.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.potteryshop.Dto.Response.Cart.CartResponse;
import com.project.potteryshop.Entity.Cart;
import com.project.potteryshop.Mapper.CartMapper;
import com.project.potteryshop.Repository.CartRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartMapper cartMapper;

    public Cart createCart() {
        Cart newCart = new Cart();

        newCart.setNumOfProduct(0);

        cartRepository.save(newCart);

        return newCart;
    }

    public List<CartResponse> getAllCart() {
        return cartRepository.findAll().stream().map(cartMapper::toCartResponse).toList();
    }
}
