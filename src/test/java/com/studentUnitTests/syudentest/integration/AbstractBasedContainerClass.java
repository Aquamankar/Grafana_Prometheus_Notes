package com.studentUnitTests.syudentest.integration;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

public abstract class  AbstractBasedContainerClass {

    //  @Container
    private  static MySQLContainer<?> MY_SQL_CONTAINER;

    static {

        MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8.0.36")
                .withDatabaseName("testdb")
                .withUsername("testuser")
                .withPassword("testpass");
        MY_SQL_CONTAINER.start(); // ✅ start container before using
    }
    @DynamicPropertySource
    static void  dynamicResouse(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url",
                MY_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username",MY_SQL_CONTAINER::getUsername);

        registry.add("spring.datasource.password",MY_SQL_CONTAINER::getPassword);
    }
}
