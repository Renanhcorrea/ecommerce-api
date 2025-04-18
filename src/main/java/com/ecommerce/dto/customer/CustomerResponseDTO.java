package com.ecommerce.dto.customer;

import com.ecommerce.dto.address.AddressDTO;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerResponseDTO {
    private String name;
    private String email;
    private AddressDTO address;
    @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "Invalid CEP format")
    private String cep;
    private String phone;
    private String taxIdentifier;
}
