package org.stve.product;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ProductRepository implements PanacheRepositoryBase<Product, Long> {
}
