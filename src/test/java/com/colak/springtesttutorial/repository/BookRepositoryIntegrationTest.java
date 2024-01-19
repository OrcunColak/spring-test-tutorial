package com.colak.springtesttutorial.repository;

import com.colak.springtesttutorial.jpa.Book;
import com.colak.springtesttutorial.testcontainers.postgres.PostgresBaseIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

class BookRepositoryIntegrationTest extends PostgresBaseIntegrationTest {

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
