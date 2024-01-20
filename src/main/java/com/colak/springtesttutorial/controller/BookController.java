package com.colak.springtesttutorial.controller;

import com.colak.springtesttutorial.jpa.Book;
import com.colak.springtesttutorial.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/book")
    public Book create(@RequestBody Book book) {
        return bookService.create(book);
    }

    @GetMapping("/book/{bookId}")
    public Book getById(@PathVariable Long bookId) {
        return bookService.getById(bookId);
    }

    @PutMapping("/book/{bookId}")
    public Book update(@RequestBody Book book) {
        return bookService.update(book);
    }

    @DeleteMapping("/{bookId}")
    public void delete(@PathVariable Long bookId) {
        bookService.delete(bookId);
    }
}
