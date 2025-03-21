package com.project.potteryshop.Dto.Request.User;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    private String name;

    @Email(message = "INVALID_EMAIL")
    private String email;
    private String phoneNumber;
    private LocalDate dob;

    @Size(min = 8, message = "INVALID_USERNAME")
    private String username;
    private String address;
    @Size(min = 6, message = "PASSWORD_INVALID")
    private String password;
}
