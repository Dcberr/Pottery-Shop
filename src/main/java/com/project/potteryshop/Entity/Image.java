package com.project.potteryshop.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.ElementCollection;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "imageId")

public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String imageId;
    @ElementCollection
    private List<String> linkImage;

    // @OneToOne
    // @JoinColumn(name = "productId")
    // private Product product;

    private String productId;
}
