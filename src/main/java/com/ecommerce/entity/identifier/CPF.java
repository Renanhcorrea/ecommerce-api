package com.ecommerce.entity.identifier;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class CPF extends TaxIdentifier{

    public CPF() {
        super();
    }

    public CPF(String identifier){
        super(identifier);
    }

}
