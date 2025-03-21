package com.project.potteryshop.Dto.Response.ProductCategory;

import java.util.List;

import com.project.potteryshop.Dto.Response.Product.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryResponse {
    private String categoryId;
    private String name;
    private int numOfProduct;
    private List<ProductResponse> products;
}
