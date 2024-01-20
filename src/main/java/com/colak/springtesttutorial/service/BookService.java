package com.colak.springtesttutorial.service;

import com.colak.springtesttutorial.jpa.Book;

public interface BookService {

    Book create(Book book);

    Book getById(Long bookId);

    Book update(Book book);

    void delete(Long bookId);

}
