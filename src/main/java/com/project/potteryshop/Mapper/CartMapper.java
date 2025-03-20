package com.project.potteryshop.Mapper;

import org.mapstruct.Mapper;

import com.project.potteryshop.Dto.Response.Cart.CartResponse;
import com.project.potteryshop.Entity.Cart;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartResponse toCartResponse(Cart cart);
}
