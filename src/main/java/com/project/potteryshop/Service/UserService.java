package com.project.potteryshop.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.potteryshop.Dto.Request.User.UserCreateRequest;
import com.project.potteryshop.Dto.Request.User.UserUpdateRequest;
import com.project.potteryshop.Dto.Response.User.UserCreateResponse;
import com.project.potteryshop.Dto.Response.User.UserResponse;
import com.project.potteryshop.Entity.User;
import com.project.potteryshop.Enum.UserRole;
import com.project.potteryshop.Enum.UserStatus;
import com.project.potteryshop.Mapper.UserMapper;
import com.project.potteryshop.Repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
// @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CartService cartService;

    public UserCreateResponse createUser(UserCreateRequest newUser) {
        User user = userMapper.toUser(newUser);
        log.info(newUser.getName());
        log.info(user.getName());

        user.setCart(cartService.createCart());

        user.setStatus(UserStatus.Active);
        user.setUserRole(UserRole.Customer);

        UserCreateResponse response = userMapper.toUserCreateResponse(user);
        userRepository.save(user);

        return response;
    }

    public List<UserResponse> getAllUser() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow();

        userMapper.toUpdateUser(user, request);

        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }
}
