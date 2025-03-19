package com.project.potteryshop.Dto.Response;

import java.time.LocalDate;
import java.util.List;

import com.project.potteryshop.Entity.Cart;
import com.project.potteryshop.Entity.Feedback;
import com.project.potteryshop.Entity.PaymentHistory;
import com.project.potteryshop.Entity.UserOrder;
import com.project.potteryshop.Enum.UserRole;
import com.project.potteryshop.Enum.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateResponse {
    private String name;
    private String email;
    private String phoneNumber;
    private UserRole userRole;
    private LocalDate dob;
    private String address;
    private UserStatus status;
    private Cart cart;
    private List<UserOrder> orders;
    private List<Feedback> feedbacks;
    private List<PaymentHistory> paymentHistories;
}
