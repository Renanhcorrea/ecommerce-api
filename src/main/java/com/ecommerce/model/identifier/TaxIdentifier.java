package com.ecommerce.model.identifier;


import jakarta.persistence.*;
import lombok.Data;

@MappedSuperclass
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class TaxIdentifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String identifier;

    public TaxIdentifier() {
    }

    public TaxIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
