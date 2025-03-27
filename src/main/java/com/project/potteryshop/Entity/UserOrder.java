package com.project.potteryshop.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.project.potteryshop.Enum.PaymentMethod;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderId")
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderId;
    // private String receiver;
    // private String phone;
    // private String address;

    private int numOfProduct;
    private float totalPrice;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference
    private User user;

    // @OneToOne
    // @JoinColumn(name = "cartId")
    // @JsonBackReference
    // private Cart cart;

    @ManyToMany(mappedBy = "orders")
    // @JoinTable(name = "order_products", joinColumns = @JoinColumn(name =
    // "orderId"), inverseJoinColumns = @JoinColumn(name = "productId"))
    @JsonManagedReference
    private List<Product> products;

    @OneToOne
    @JoinColumn(name = "paymentId")
    @JsonManagedReference
    private PaymentHistory paymentHistory;
}