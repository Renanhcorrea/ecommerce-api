package com.ecommerce.dto.customer;

import com.ecommerce.dto.address.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Valid
    private AddressDTO address;

    @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "Invalid CEP format")
    private String cep;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @NotBlank(message = "Tax identifier is required")
    private String taxIdentifier;
}
