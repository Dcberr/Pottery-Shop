package com.project.potteryshop.Dto.Request.Authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailReceiverRequest {
    private String toEmail;
    private String resetLink;
}
