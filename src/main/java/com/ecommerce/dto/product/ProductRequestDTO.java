package com.ecommerce.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;
    private String description;
    @NotNull(message = "price is required")
    @Min(value = 0, message = "Price must be more than 0")
    private Double price;
    @NotBlank(message = "Unit is required")
    private String unit;
    private String type;
    @NotNull(message = "Group type is required")
    @Min(value = 1, message = "Group Type must be more than 0")
    private Integer groupType;
}
