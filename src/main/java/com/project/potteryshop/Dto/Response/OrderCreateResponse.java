package com.project.potteryshop.Dto.Response;

import java.util.List;

import com.project.potteryshop.Entity.Product;
import com.project.potteryshop.Enum.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateResponse {
    private String orderId;
    private String receiver;
    private String phone;
    private String address;
    private int numOfProduct;
    private float totalPrice;
    private PaymentMethod paymentMethod;
    private List<Product> products;
}
