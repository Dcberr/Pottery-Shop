package com.project.potteryshop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.potteryshop.Dto.Request.UserCreateRequest;
import com.project.potteryshop.Dto.Response.UserCreateResponse;
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

    public UserCreateResponse createUser(UserCreateRequest newUser) {
        User user = userMapper.toUser(newUser);
        log.info(newUser.toString());
        log.info(user.toString());

        user.setStatus(UserStatus.Active);
        user.setUserRole(UserRole.Customer);

        UserCreateResponse response = userMapper.toUserCreateResponse(user);
        userRepository.save(user);

        return response;
    }
}
