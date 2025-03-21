package com.project.potteryshop.Mapper;

import org.mapstruct.Mapper;

import com.project.potteryshop.Dto.Response.Product.ProductResponse;
import com.project.potteryshop.Entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toProductResponse(Product product);
}
