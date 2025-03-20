package com.project.potteryshop.Dto.Response.Cart;

import java.util.List;

import com.project.potteryshop.Entity.Product;
import com.project.potteryshop.Entity.UserOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    private String cartId;
    private int numOfProduct;
    private List<Product> products;
    private UserOrder order;
}
