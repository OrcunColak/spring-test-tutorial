package com.colak.springtesttutorial.service;

import com.colak.springtesttutorial.jpa.Book;
import com.colak.springtesttutorial.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This test shows how to use @MockBean and @SpyBean
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BookService.class)
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @SpyBean
    private HelloBean helloBean;

    @Test
    void findById() {
        Book mockBook = new Book(1L, "John Doe", "isbn", LocalDate.now(), false);
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(mockBook));

        Book book = bookService.findById(1L);

        assertEquals("John Doe", book.getAuthor());
        Mockito.verify(bookRepository, Mockito.times(1)).findById(1L);

        Mockito.verify(helloBean, Mockito.times(1)).sayHello();
    }
}