package com.ecommerce.dto.stock;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class StockUpdateDTO {
    private Long productId;
    @Min(value = 0, message = "Quantity must not be negative")
    private Long quantity;
    private String location;
}
