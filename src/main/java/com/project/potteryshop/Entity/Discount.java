package com.project.potteryshop.Entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String discountId;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;

    @ManyToMany(mappedBy = "discounts")
    private List<Product> products;
}
