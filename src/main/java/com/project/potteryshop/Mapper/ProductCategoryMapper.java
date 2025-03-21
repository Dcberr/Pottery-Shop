package com.project.potteryshop.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.potteryshop.Dto.Response.ProductCategory.ProductCategoryResponse;
import com.project.potteryshop.Entity.ProductCategory;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {
    @Mapping(target = "products", ignore = true)
    ProductCategoryResponse toProductCategoryResponse(ProductCategory category);
}
