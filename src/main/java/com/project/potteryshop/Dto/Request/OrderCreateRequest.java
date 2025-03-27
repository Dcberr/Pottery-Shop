package com.project.potteryshop.Dto.Request;

import java.util.List;

import com.project.potteryshop.Enum.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest {
    private String userId;
    private List<String> productsId;
    private PaymentMethod paymentMethod;
}
