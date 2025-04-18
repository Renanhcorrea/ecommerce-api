package com.ecommerce.dto.product;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private String name;
    private String description;
    private Double price;
    private String unit;
    private String type;
    private Integer groupType;
}
