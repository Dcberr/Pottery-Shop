package com.project.potteryshop.Configuration;

import java.util.HashSet;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.potteryshop.Entity.Role;
import com.project.potteryshop.Entity.User;
import com.project.potteryshop.Enum.UserRole;
import com.project.potteryshop.Repository.RoleRepository;
import com.project.potteryshop.Repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {
    private final PasswordEncoder passwordEncoder;
    private static final String ADMIN_USER_NAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        log.info("Initializing application.....");
        return args -> {
            if (userRepository.findByUsername(ADMIN_USER_NAME).isEmpty()) {
                roleRepository.save(Role.builder()
                        .name(UserRole.CUSTOMER.name())
                        .description("Customer Role")
                        .build());

                Role adminRole = roleRepository.save(Role.builder()
                        .name(UserRole.ADMIN.name())
                        .description("Admin Role")
                        .build());

                roleRepository.save(Role.builder()
                        .name(UserRole.SELLER.name())
                        .description("Seller Role")
                        .build());

                HashSet<Role> roles = new HashSet<>();
                roles.add(adminRole);

                User user = User.builder()
                        .username(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .userRole(roles)
                        .build();

                userRepository.save(user);
            }
            log.info("Application initialization completed .....");
        };
    }
}
