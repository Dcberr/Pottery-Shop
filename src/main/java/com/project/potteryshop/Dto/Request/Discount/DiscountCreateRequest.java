package com.project.potteryshop.Dto.Request.Discount;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCreateRequest {
    private String name;
    private LocalDate startDate;
    private int percentDiscount;
    private float minimum;
    // private float maximum;
    private LocalDate endDate;
    private String description;
    private List<String> productsId;
}
