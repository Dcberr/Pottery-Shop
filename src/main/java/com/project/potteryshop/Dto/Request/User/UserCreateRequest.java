package com.project.potteryshop.Dto.Request.User;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate dob;
    private String username;
    private String address;
    private String password;
}
