package com.project.potteryshop.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.potteryshop.Dto.Request.Discount.DiscountCreateRequest;
import com.project.potteryshop.Entity.Discount;

@Mapper(componentModel = "spring")
public interface DiscountMapper {
    @Mapping(target = "discountId", ignore = true)
    @Mapping(target = "products", ignore = true)
    Discount toDiscount(DiscountCreateRequest request);
}
