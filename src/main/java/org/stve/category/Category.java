package org.stve.category;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.stve.product.Product;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Category extends PanacheEntity {

    @NotBlank(message = "name is required")
    @Size(min = 3, message = "name must have at least 3 characters")
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
