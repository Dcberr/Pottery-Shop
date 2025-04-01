package com.project.potteryshop.Dto.Response.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.project.potteryshop.Entity.Cart;
import com.project.potteryshop.Entity.Feedback;
import com.project.potteryshop.Entity.MessageRoom;
import com.project.potteryshop.Entity.MessageRoomMember;
import com.project.potteryshop.Entity.PaymentHistory;
import com.project.potteryshop.Entity.Role;
import com.project.potteryshop.Entity.UserOrder;
import com.project.potteryshop.Enum.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateResponse {
    private String userId;
    private String name;
    private String email;
    private Date lastLogin;
    private String phoneNumber;
    private Set<Role> userRole;
    private LocalDate dob;
    private String address;
    private UserStatus status;
    private Cart cart;
    private List<UserOrder> orders;
    private List<Feedback> feedbacks;
    private List<PaymentHistory> paymentHistories;
    private List<MessageRoom> messageRooms;
    private List<MessageRoomMember> messageRoomMembers;
}
