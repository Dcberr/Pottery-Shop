package com.project.potteryshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.potteryshop.Entity.UserOrder;

@Repository
public interface OrderRepository extends JpaRepository<UserOrder, String> {
    UserOrder findByOrderId(String orderId);
}
