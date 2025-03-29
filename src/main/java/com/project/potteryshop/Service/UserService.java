package com.project.potteryshop.Service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.potteryshop.Dto.Request.User.UserCreateRequest;
import com.project.potteryshop.Dto.Request.User.UserUpdateRequest;
import com.project.potteryshop.Dto.Response.User.UserCreateResponse;
import com.project.potteryshop.Dto.Response.User.UserResponse;
import com.project.potteryshop.Entity.Cart;
import com.project.potteryshop.Entity.Role;
import com.project.potteryshop.Entity.User;
import com.project.potteryshop.Enum.UserRole;
import com.project.potteryshop.Enum.UserStatus;
import com.project.potteryshop.Mapper.UserMapper;
import com.project.potteryshop.Repository.CartRepository;
import com.project.potteryshop.Repository.RoleRepository;
import com.project.potteryshop.Repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
// @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CartService cartService;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserCreateResponse createUser(UserCreateRequest newUser) {
        User user = userMapper.toUser(newUser);
        log.info(newUser.getName());
        log.info(user.getName());

        user.setStatus(UserStatus.ACTIVE);

        HashSet<Role> roles = new HashSet<>();
        roleRepository.findById(UserRole.CUSTOMER.name()).ifPresent(roles::add);
        user.setUserRole(roles);

        // PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(user);

        Cart newCart = cartService.createCart();
        newCart.setUser(user);
        user.setCart(newCart);

        cartRepository.save(newCart);

        UserCreateResponse response = userMapper.toUserCreateResponse(user);

        return response;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUser() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public UserResponse getMyInfor() {
        SecurityContext context = SecurityContextHolder.getContext();

        String name = context.getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow();

        return userMapper.toUserResponse(user);
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow();

        userMapper.toUpdateUser(user, request);

        // PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        if (!request.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse getUserById(String userId) {
        return userMapper.toUserResponse(userRepository.findByUserId(userId));
    }
}
