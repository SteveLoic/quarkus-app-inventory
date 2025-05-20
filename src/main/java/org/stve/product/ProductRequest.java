package org.stve.product;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "sku is required")
    private String sku;

    @Positive(message = "price must be a positive value")
    private BigDecimal price;

    @Positive(message = "stockQuantity must be a positive value")
    @Min(value = 0, message = "Stock Quantity cannot lesser than zero")
    private Integer stockQuantity;


    @NotBlank(message = "description is required")
    private String description;

    private LocalDateTime expiryDate;

    private String categoryName;
}
