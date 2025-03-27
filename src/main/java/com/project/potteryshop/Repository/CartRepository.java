package com.project.potteryshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.potteryshop.Entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    Cart findByUser_UserId(String userId);

    Cart findByCartId(String cartId);
}