package com.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Product {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String unit;
    private String type;

    @Column(nullable = false)
    private Integer groupType;
}
