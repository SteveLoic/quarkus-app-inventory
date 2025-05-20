package org.stve.category;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stve.common.AbstractionBaseTest;

import java.util.Arrays;
import java.util.List;

@QuarkusTest
public class CategoryRepositoryTest extends AbstractionBaseTest {

    @Inject
    CategoryRepository categoryRepository;

    @Transactional
    @BeforeEach
    void cleanUp() {
        categoryRepository.deleteAll();
    }


    @Test
    @Transactional
    void givenCategory_WhenSave_ReturnCategory() {
        Category categoryToSave = Category.builder()
                .name("Electronics")
                .build();
        categoryRepository.persist(categoryToSave);
        Category savedCategory = categoryRepository.find("name", categoryToSave.getName()).firstResult();
        Assertions.assertNotNull(savedCategory);
    }

    @Test
    @Transactional
    void givenCategoryList_whenSave_ReturnAllCategoryy() {
        List<Category> categories = Arrays.asList(
                Category.builder().name("Eletronics").build(),
                Category.builder().name("Sports").build(),
                Category.builder().name("Pharaticals").build()
        );
        categoryRepository.persist(categories);

        List<Category> allCategoriesFounded = categoryRepository.findAll().list();

        Assertions.assertFalse(allCategoriesFounded.isEmpty());
        Assertions.assertEquals(allCategoriesFounded.size(), 3);
    }


    @Test
    @Transactional
    void givenCategory_whenDelete_ReturnNull() {
        Category categoryToSave = Category.builder()
                .name("Electronics")
                .build();
        categoryRepository.persist(categoryToSave);
        Category savedCategory = categoryRepository.find("name", categoryToSave.getName()).firstResult();

        categoryRepository.delete(savedCategory);
        Category deletedCategory = categoryRepository.find("name", categoryToSave.getName()).firstResult();
        Assertions.assertNull(deletedCategory);

    }
}
