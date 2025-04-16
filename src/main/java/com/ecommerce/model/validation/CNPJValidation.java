package com.ecommerce.model.validation;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import org.springframework.stereotype.Component;

@Component
public class CNPJValidation {

    private final CNPJValidator cnpjValidator = new CNPJValidator();

    public boolean isValidCNPJ(String cnpj){
        try {
            cnpjValidator.assertValid(cnpj);
            return true;
        } catch (InvalidStateException e ){
            return false;
        }
    }
}
