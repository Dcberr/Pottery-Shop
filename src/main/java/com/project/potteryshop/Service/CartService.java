package com.project.potteryshop.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.potteryshop.Dto.Response.Cart.CartResponse;
import com.project.potteryshop.Entity.Cart;
import com.project.potteryshop.Entity.Product;
import com.project.potteryshop.Mapper.CartMapper;
import com.project.potteryshop.Repository.CartRepository;
import com.project.potteryshop.Repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductRepository productRepository;

    public Cart createCart() {
        Cart newCart = new Cart();

        newCart.setNumOfProduct(0);

        cartRepository.save(newCart);

        return newCart;
    }

    public List<CartResponse> getAllCart() {
        return cartRepository.findAll().stream().map(cartMapper::toCartResponse).toList();
    }

    public Cart addNewProduct(String userId, String productId) {
        Cart cart = cartRepository.findByUser_UserId(userId);

        List<Product> products = cart.getProducts();
        if (products == null) {
            products = new ArrayList<>();
        }

        products.add(productRepository.findByProductId(productId));
        cart.setProducts(products);
        cart.setNumOfProduct(products.size());

        cartRepository.save(cart);

        return cart;
    }

    public Cart getCartById(String cartId) {
        return cartRepository.findByCartId(cartId);
    }
}
