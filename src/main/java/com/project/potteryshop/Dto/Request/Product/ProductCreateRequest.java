package com.project.potteryshop.Dto.Request.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {
    private String name;
    private String description;
    private int remainingQuantity;
    private float price;
    private String material;
    private String publisher;
    private String design;
    private String color;
    private String pattern;
    private String brand;
}
