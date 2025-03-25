package com.project.potteryshop.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.potteryshop.Dto.Request.Product.ProductCreateRequest;
import com.project.potteryshop.Dto.Request.Product.ProductItemRequest;
import com.project.potteryshop.Entity.ProductItem;

@Mapper(componentModel = "spring")
public interface ProductItemMapper {
    @Mapping(target = "itemId", ignore = true)
    @Mapping(target = "product", ignore = true)
    ProductItem toProductItem(ProductItemRequest request);

    ProductItemRequest toBodyProductItem(ProductCreateRequest createRequest);
}
