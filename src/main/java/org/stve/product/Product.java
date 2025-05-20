package org.stve.product;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.stve.category.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product extends PanacheEntity {

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "description is required")
    private String description;

    @Positive(message = "stockQuantity must be a positive value")
    @Min(value = 0, message = "Stock Quantity cannot lesser than zero")
    private Integer stockQuantity;

    @NotBlank(message = "sku is required")
    private String sku;

    @Positive(message = "stockQuantity must be a positive")
    private BigDecimal price;

    @UpdateTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(updatable = true)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
