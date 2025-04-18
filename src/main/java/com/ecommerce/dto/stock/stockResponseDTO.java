package com.ecommerce.dto.stock;

import lombok.Data;

@Data
public class stockResponseDTO {
    private Long id;
    private Long productId;
    private Long quantity;
    private String location;
}
