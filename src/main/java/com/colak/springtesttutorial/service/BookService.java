package com.colak.springtesttutorial.service;

import com.colak.springtesttutorial.jpa.Book;
import com.colak.springtesttutorial.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final HelloBean helloBean;

    @Transactional
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public Book findById(Long bookId) {
        helloBean.sayHello ();
        return bookRepository.findById(bookId)
                .orElseThrow();
    }

    @Transactional
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public void delete(Long bookId) {
        var existingBook = bookRepository.findById(bookId)
                .orElseThrow();

        existingBook.setDeleted(true);
        bookRepository.save(existingBook);
    }
}
