package com.ecommerce.dto.product;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ProductUpdateDTO {
    private String name;
    private String description;
    @Min(value = 0, message = "Price must be more than 0")
    private Double price;
    private String unit;
    private String type;
    @Min(value = 1, message = "Group Type must be more than 0")
    private Integer groupType;
}
