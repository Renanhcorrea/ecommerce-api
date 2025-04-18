package com.ecommerce.dto.address;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddressDTO {
    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "Number is required")
    private String number;

    @NotBlank(message = "Neighborhood is required")
    private String neighborhood;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Postal Code is required")
    private String postalCode;
}
