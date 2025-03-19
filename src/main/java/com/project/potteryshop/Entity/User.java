package com.project.potteryshop.Entity;

import java.time.LocalDate;
import java.util.List;

import com.project.potteryshop.Enum.UserRole;
import com.project.potteryshop.Enum.UserStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate dob;
    private String username;
    private String address;
    private String password;
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    @OneToMany(mappedBy = "user")
    private List<UserOrder> orders;

    @OneToMany(mappedBy = "user")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "user")
    private List<PaymentHistory> paymentHistories;
}
