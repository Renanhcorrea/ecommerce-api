package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private String location;
}
