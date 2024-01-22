package com.colak.springtesttutorial.repository;

import com.colak.springtesttutorial.jpa.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDate;

/**
 * This annotation selectively loads beans related to Spring Data JPA.
 * This prevents unnecessary additional beans from being loaded, contributing to faster test execution.
 *
 * @DataJpaTest automatically starts a transaction for each test method and rolls back the transaction at the end of the test.
 * This prevents tests from affecting each other, maintaining consistent test results.
 */

// Disable the SQL query logging in @DataJpaTest
@DataJpaTest(showSql = false)
// Disable the transactional and roll back in @DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
// Disable H2
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class BookRepositoryDataJpaTest {

    @Container
    @ServiceConnection
    public static PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres"))
                    .withPassword("inmemory")
                    .withUsername("inmemory");

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testSave() {
        var book = new Book(1L, "John Doe", "123", LocalDate.now(), false);
        var createdBook = bookRepository.save(book);
        Assertions.assertNotNull(createdBook.getId());
        Assertions.assertEquals(createdBook.getAuthor(), book.getAuthor());
        Assertions.assertEquals(createdBook.getIsbn(), book.getIsbn());
        Assertions.assertEquals(createdBook.getPublishedDate(), book.getPublishedDate());
        Assertions.assertFalse(createdBook.isDeleted());
    }

    @Test
    void testFindById() {
        var createdBook = bookRepository.findById(1L);
        Assertions.assertNotNull(createdBook);
    }

}
