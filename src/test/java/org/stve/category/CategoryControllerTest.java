package org.stve.category;


import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.stve.common.AbstractionBaseTest;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class CategoryControllerTest extends AbstractionBaseTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Inject
    CategoryRepository categoryRepository;

    @Transactional
    @BeforeEach
    void cleanUp(TestInfo testInfo) {
        categoryRepository.deleteAll();
        if (testInfo.getTestMethod().isPresent()) {
            String methodName = testInfo.getTestMethod().get().getName();
            if (methodName.equals("givenSavedCategoryInRepository_WhenGetByCategoryName_ReturnWithCategory") ||
                    methodName.equals("givenSavedCategoryInRepository_WhenUpdateCategoryName_ReturnUpdatedCategory") ||
                    methodName.equals("givenSavedCategoryInRepository_WhenDeleteCategoryName_ReturnStatusCode200WithSuccessMessage")

            ) {
                Category category = Category.builder().name("Sport").build();
                categoryRepository.persist(category);
            }
        }
    }

    @Test
    void givenCategory_WhenSave_ReturnSavedCategoryWithStatusAndMessage() throws Exception {
        CategoryRequest categoryRequest = CategoryRequest.builder().name("Electronics").build();
        String categoryRequestJson = objectMapper.writeValueAsString(categoryRequest);

        given()
                .contentType(ContentType.JSON)
                .body(categoryRequestJson)
                .when()
                .post("/categories/category/add")
                .then()
                .statusCode(200)
                .body("message", equalTo("Category successfully added"))
                .body("categoryResponse.name", equalTo("Electronics"))
                .body("status", equalTo(200));
    }

    @Test
    @Transactional
    void givenSavedCategoryInRepository_WhenGetByCategoryName_ReturnWithCategory() {
        String categoryName = "Sport";
        given()
                .pathParams("categoryName", categoryName)
                .when()
                .get("/categories/{categoryName}")
                .then()
                .statusCode(200)
                .body("categoryResponse.name", equalTo("Sport"))
                .body("status", equalTo(200));

    }

    @Test
    @Transactional
    void givenSavedCategoryInRepository_WhenUpdateCategoryName_ReturnUpdatedCategory() throws Exception {
        String categoryName = "Sport";
        CategoryRequest categoryRequest = CategoryRequest.builder().name("Pharmatical").build();
        String categoryRequestJson = objectMapper.writeValueAsString(categoryRequest);

        given()
                .contentType(ContentType.JSON)
                .pathParams("categoryName", categoryName)
                .body(categoryRequestJson)
                .put("/categories/{categoryName}/update")
                .then()
                .statusCode(200)
                .body("categoryResponse.name", equalTo("Pharmatical"))
                .body("message", equalTo("Category successfully updated"))
                .body("status", equalTo(200));
    }

    @Test
    @Transactional
    void givenSavedCategoryInRepository_WhenDeleteCategoryName_ReturnStatusCode200WithSuccessMessage() throws Exception {
        String categoryName = "Sport";
        given()
                .pathParams("categoryName", categoryName)
                .delete("/categories/{categoryName}/delete")
                .then()
                .statusCode(200)
                .body("message", equalTo("Category has been successfully deleted"));

    }
}
