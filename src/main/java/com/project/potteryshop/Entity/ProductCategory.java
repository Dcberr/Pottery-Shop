package com.project.potteryshop.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String categoryId;
    private String name;
    private int numOfProduct;

    @OneToMany(mappedBy = "productCategory")
    private List<Product> products;

}
