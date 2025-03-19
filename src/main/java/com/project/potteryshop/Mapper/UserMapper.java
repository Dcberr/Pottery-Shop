package com.project.potteryshop.Mapper;

import org.mapstruct.Mapper;

import com.project.potteryshop.Dto.Request.UserCreateRequest;
import com.project.potteryshop.Dto.Response.UserCreateResponse;
import com.project.potteryshop.Entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest request);

    UserCreateResponse toUserCreateResponse(User user);
}
