package org.stve.category;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;


@ApplicationScoped
public class CategoryRepository implements PanacheRepositoryBase<Category, Long> {

    public Optional<Category> findByName(String name) {
        return find("name", name).firstResultOptional();
    }
}
