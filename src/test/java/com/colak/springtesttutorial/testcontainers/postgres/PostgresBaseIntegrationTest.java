package com.colak.springtesttutorial.testcontainers.postgres;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public abstract class PostgresBaseIntegrationTest {

    @Container
    @ServiceConnection
    public static PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres"))
                    .withPassword("inmemory")
                    .withUsername("inmemory");


}
