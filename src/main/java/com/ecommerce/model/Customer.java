package com.ecommerce.model;

import com.ecommerce.model.identifier.TaxIdentifier;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String address;

    @Column(nullable = false)
    private String phone;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tax_identifier_id")
    private TaxIdentifier taxIdentifier;

    @Column(name="created_at", updatable = false)
    private LocalDateTime createdDate;

    public Customer() {
    }

    public Customer(String name, String email, String address, String phone, TaxIdentifier taxIdentifier) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.taxIdentifier = taxIdentifier;
    }
}
