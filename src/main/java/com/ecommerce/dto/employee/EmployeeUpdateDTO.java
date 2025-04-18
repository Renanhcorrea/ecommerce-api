package com.ecommerce.dto.employee;

import com.ecommerce.dto.address.AddressDTO;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmployeeUpdateDTO {
    private Long id;
    private String name;
    private String login;
    private String email;
    private String role;
    @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "Invalid CEP format")
    private String cep;
    private AddressDTO address;
    private String taxIdentifier;
}
