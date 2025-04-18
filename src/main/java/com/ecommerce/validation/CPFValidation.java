package com.ecommerce.validation;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import org.springframework.stereotype.Component;


@Component
public class CPFValidation {

    private final CPFValidator cpfValidator = new CPFValidator();

    public boolean isValidCPF(String cpf){
        try {
            cpfValidator.assertValid(cpf);
            return true;
        } catch (InvalidStateException e){
            return false;
        }
    }



}
