package org.stve.product;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stve.category.Category;
import org.stve.category.CategoryRepository;
import org.stve.common.AbstractionBaseTest;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@QuarkusTest
public class ProductControllerTest extends AbstractionBaseTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Inject
    ProductRepository productRepository;

    @Inject
    CategoryRepository categoryRepository;


    @Transactional
    @BeforeEach
    void cleanUp(){
        productRepository.deleteAll();
        categoryRepository.deleteAll();

        List<Category> categories = Arrays.asList(
                Category.builder().name("Eletronics").build(),
                Category.builder().name("Sports").build()
        );
        categoryRepository.persist(categories);
    }


    @Transactional
    @Test
    void givenProduct_WhenSave_returnSavedProductWithStatusAndMessage() throws  Exception {
        ProductRequest  productRequest = ProductRequest.builder()
                .name("Samsung Galaxy 2")
                .categoryName("Eletronics")
                .sku("ZG011AQA")
                .stockQuantity(15)
                .price(new BigDecimal(320.00))
                .build();
        String productRequestJson = objectMapper.writeValueAsString(productRequest);

        given()
                .contentType(ContentType.JSON)
                .body(productRequestJson)
                .then()
                .statusCode(200)
                .body("message", equalTo("Product successfully added"))
                .body("productResponse.name", equalTo("Samsung Galaxy 2"))
                .body("status",equalTo(200));
    }




}
