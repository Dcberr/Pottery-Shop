package com.project.potteryshop.Entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "discountId")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String discountId;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;

    @ManyToMany(mappedBy = "discounts")
    @JsonBackReference
    private List<Product> products;
}
