package org.stve.product;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stve.category.Category;
import org.stve.category.CategoryRepository;
import org.stve.common.AbstractionBaseTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@QuarkusTest
public class ProductRepositoryTest extends AbstractionBaseTest {

    @Inject
    ProductRepository productRepository;

    @Inject
    CategoryRepository categoryRepository;

    @Transactional
    @BeforeEach
    void cleanUp() {
        productRepository.deleteAll();
        categoryRepository.deleteAll();
        List<Category> categories = Arrays.asList(
                Category.builder().name("Electronics").build(),
                Category.builder().name("Sports").build()
        );
        categoryRepository.persist(categories);
    }

    private Category findCategoryByName(String categoryName) {
        return   categoryRepository.find("name", categoryName).firstResult();
    }

    @Test
    @Transactional
    void givenProduct_WhenSave_ReturnProduct() {
        String categoryName = "Electronics";
        Category savedCategory = findCategoryByName(categoryName);
        Product productToBeSave = Product.builder()
                .name("Samsung Galaxy 2")
                .category(savedCategory)
                .sku("ZG011AQA")
                .stockQuantity(15)
                .price(new BigDecimal(320.00))
                .build();
        productRepository.persist(productToBeSave);

        Product savedProduct = productRepository.find("name", productToBeSave.getName()).firstResult();
        Assertions.assertNotNull(savedProduct);
        Assertions.assertEquals(savedProduct.getName(), productToBeSave.getName());
    }

    @Test
    @Transactional
    void givenListProduct_WhenSave_ReturnAllProductInDatabase(){
        List<Product> products = new ArrayList<>();
        Category savedCategoryElectronics = findCategoryByName("Electronics");
        Product electronicsProductToBeSave = Product.builder()
                .name("Samsung Galaxy 2")
                .category(savedCategoryElectronics)
                .sku("ZG011AQA")
                .stockQuantity(15)
                .price(new BigDecimal(320.00))
                .build();
        products.add(electronicsProductToBeSave);
        Category savedCategorySports = findCategoryByName("Sports");
        Product sportsProductToBeSave = Product.builder()
                .name("Nike Runner")
                .category(savedCategorySports)
                .sku("ZG011AQB")
                .stockQuantity(15)
                .price(new BigDecimal(150.00))
                .build();
        products.add(sportsProductToBeSave);
        productRepository.persist(products);

        List<Product> savedProducts = productRepository.findAll().list();
        Assertions.assertFalse(savedProducts.isEmpty());
        Assertions.assertEquals(savedProducts.size(), 2);
    }

    @Test
    @Transactional
    void givenProduct_WhenDelete_ReturnNull(){
        String categoryName = "Electronics";
        Category savedCategory = findCategoryByName(categoryName);
        Product productToBeSave = Product.builder()
                .name("Samsung Galaxy 2")
                .category(savedCategory)
                .sku("ZG011AQA")
                .stockQuantity(15)
                .price(new BigDecimal(320.00))
                .build();
        productRepository.persist(productToBeSave);

        Product savedProduct = productRepository.find("name", productToBeSave.getName()).firstResult();

        productRepository.delete(savedProduct);
        Product deletedProduct = productRepository.find("name", savedProduct.getName()).firstResult();

        Assertions.assertNull(deletedProduct);
    }


}
