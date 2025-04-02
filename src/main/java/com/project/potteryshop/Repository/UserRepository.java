package com.project.potteryshop.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.potteryshop.Entity.User;
import com.project.potteryshop.Enum.UserStatus;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUserId(String userId);

    Optional<User> findByUsername(String username);

    User findByEmail(String email);

    List<User> findAllByStatus(UserStatus userStatus);
}
