package org.stve.common;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;


public abstract class AbstractionBaseTest {

    @Container
    private static PostgreSQLContainer POSTGRES_SQL_CONTAINER;

    static {
        POSTGRES_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:15")
                .withUsername("username")
                .withPassword("password")
                .withDatabaseName("quarkus_db");

    }

    @BeforeAll
    public static void beforeAll() {
        POSTGRES_SQL_CONTAINER.start();
        System.setProperty("quarkus.datasource.jdbc.url", POSTGRES_SQL_CONTAINER.getJdbcUrl());
        System.setProperty("quarkus.datasource.username", POSTGRES_SQL_CONTAINER.getUsername());
        System.setProperty("quarkus.datasource.password", POSTGRES_SQL_CONTAINER.getPassword());
    }

    @AfterAll
    public static void afterAll() {
        POSTGRES_SQL_CONTAINER.stop();
    }

    @Test
    void isRunning() {
        Assertions.assertTrue(POSTGRES_SQL_CONTAINER.isRunning());
    }


}
