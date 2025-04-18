package com.ecommerce.dto.stock;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StockRequestDTO {

    @NotNull(message = "ID is required")
    private Long productId;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must not be negative")
    private Long quantity;

    @NotBlank(message = "Location is required")
    private String location;
}
