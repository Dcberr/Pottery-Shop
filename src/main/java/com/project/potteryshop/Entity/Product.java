package com.project.potteryshop.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;
    private String name;
    private String description;
    private int remainingQuantity;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private ProductCategory productCategory;

    @OneToOne
    @JoinColumn(name = "imageId")
    private Image image;

    @OneToOne
    @JoinColumn(name = "itemId")
    private ProductItem productItem;

    @ManyToMany
    @JoinTable(name = "product_discounts", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "discountId"))
    private List<Discount> discounts;

    @ManyToMany
    @JoinTable(name = "product_orders", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "orderId"))
    private List<UserOrder> orders;

    @OneToMany(mappedBy = "product")
    private List<Feedback> feedbacks;

    @ManyToMany(mappedBy = "products")
    // @JoinTable(name = "product_carts", joinColumns = @JoinColumn(name =
    // "productId"), inverseJoinColumns = @JoinColumn(name = "cartId"))
    private List<Cart> carts;
}
