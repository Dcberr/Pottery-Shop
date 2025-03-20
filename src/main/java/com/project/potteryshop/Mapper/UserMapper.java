package com.project.potteryshop.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.project.potteryshop.Dto.Request.User.UserCreateRequest;
import com.project.potteryshop.Dto.Request.User.UserUpdateRequest;
import com.project.potteryshop.Dto.Response.User.UserCreateResponse;
import com.project.potteryshop.Dto.Response.User.UserResponse;
import com.project.potteryshop.Entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "feedbacks", ignore = true)
    @Mapping(target = "paymentHistories", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "userRole", ignore = true)
    User toUser(UserCreateRequest request);

    UserCreateResponse toUserCreateResponse(User user);

    UserResponse toUserResponse(User user);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "feedbacks", ignore = true)
    @Mapping(target = "paymentHistories", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "userRole", ignore = true)
    void toUpdateUser(@MappingTarget User user, UserUpdateRequest request);
}
