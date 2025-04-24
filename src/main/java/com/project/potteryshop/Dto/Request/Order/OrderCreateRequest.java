package com.project.potteryshop.Dto.Request.Order;

import java.util.List;
import java.util.Map;

import com.project.potteryshop.Enum.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreateRequest {
    private String userId;
    private List<String> productsId;
    private PaymentMethod paymentMethod;
    private Map<String, String> discountMap; // productId -> discountId
}
