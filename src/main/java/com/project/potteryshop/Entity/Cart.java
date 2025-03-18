package com.project.potteryshop.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String cartId;
    private int numOfProduct;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToMany
    @JoinTable(name = "cart_products", joinColumns = @JoinColumn(name = "cartId"), inverseJoinColumns = @JoinColumn(name = "productId"))
    private List<Product> products;

    @OneToOne
    @JoinColumn(name = "orderId")
    private UserOrder order;

}
