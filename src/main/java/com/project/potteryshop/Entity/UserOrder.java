package com.project.potteryshop.Entity;

import java.util.List;
import com.project.potteryshop.Enum.PaymentMethod;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderId;

    private int numOfProduct;
    private float totalPrice;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    @ManyToMany(mappedBy = "orders")
    // @JoinTable(name = "order_products", joinColumns = @JoinColumn(name =
    // "orderId"), inverseJoinColumns = @JoinColumn(name = "productId"))
    private List<Product> products;

    @OneToOne
    @JoinColumn(name = "paymentId")
    private PaymentHistory paymentHistory;
}