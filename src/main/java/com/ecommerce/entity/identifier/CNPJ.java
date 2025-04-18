package com.ecommerce.entity.identifier;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class CNPJ extends TaxIdentifier{

    public CNPJ() {
        super();
    }

    public CNPJ(String identifier) {
        super(identifier);
    }
}
