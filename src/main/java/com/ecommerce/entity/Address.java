package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public abstract class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String Street;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String postalCode;

}
