package com.project.potteryshop.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "productId")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;
    private String name;
    private String description;
    private int remainingQuantity;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonBackReference
    private ProductCategory productCategory;

    @OneToOne
    @JoinColumn(name = "imageId")
    @JsonManagedReference
    private Image image;

    @OneToOne
    @JoinColumn(name = "itemId")
    @JsonManagedReference
    private ProductItem productItem;

    @ManyToMany
    @JoinTable(name = "product_discounts", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "discountId"))
    @JsonManagedReference
    private List<Discount> discounts;

    @ManyToMany
    @JoinTable(name = "product_orders", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "orderId"))
    @JsonBackReference
    private List<UserOrder> orders;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<Feedback> feedbacks;

    @ManyToMany(mappedBy = "products")
    // @JoinTable(name = "product_carts", joinColumns = @JoinColumn(name =
    // "productId"), inverseJoinColumns = @JoinColumn(name = "cartId"))
    @JsonBackReference
    private List<Cart> carts;
}
