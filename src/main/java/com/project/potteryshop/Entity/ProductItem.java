package com.project.potteryshop.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String itemId;
    private float price;
    private String material;
    private String publisher;
    private String design;
    private String color;
    private String pattern;
    private String brand;

    @OneToOne
    @JoinColumn(name = "productId")
    private Product product;
}
