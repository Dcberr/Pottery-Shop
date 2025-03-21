package com.project.potteryshop.Dto.Response.Product;

import java.util.List;

import com.project.potteryshop.Entity.Cart;
import com.project.potteryshop.Entity.Discount;
import com.project.potteryshop.Entity.Feedback;
import com.project.potteryshop.Entity.Image;
import com.project.potteryshop.Entity.ProductItem;
import com.project.potteryshop.Entity.UserOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private String productId;
    private String name;
    private String description;
    private int remainingQuantity;
    // private ProductCategory productCategory;
    private Image image;
    private ProductItem productItem;
    private List<Discount> discounts;
    private List<UserOrder> orders;
    private List<Feedback> feedbacks;
    private List<Cart> carts;
}
