package org.stve.product;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse {
    private Long id;

    private Long categoryId;

    private Long productId;

    private Long supplierId;

    private String name;

    private String sku;

    private BigDecimal price;

    private Integer stockQuantity;

    private String description;


    private LocalDateTime expiryDate;

    @JsonIgnore
    private LocalDateTime updatedAt;

    @JsonIgnore
    private LocalDateTime createdAt;

}




