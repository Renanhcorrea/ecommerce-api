package com.ecommerce.dto.employee;


import com.ecommerce.dto.address.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmployeeRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "login is required")
    private String login;

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Role is required")
    private String role;

    @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "Invalid CEP format")
    private String cep;

    @Valid
    private AddressDTO address;

    @NotBlank(message = "Tax identifier is required")
    private String taxIdentifier;

}
