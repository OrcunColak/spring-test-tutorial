package com.colak.springtesttutorial.service.impl;

import com.colak.springtesttutorial.jpa.Book;
import com.colak.springtesttutorial.repository.BookRepository;
import com.colak.springtesttutorial.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    @Transactional
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow();
    }

    @Override
    @Transactional
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void delete(Long bookId) {
        var existingBook = bookRepository.findById(bookId)
                .orElseThrow();

        existingBook.setDeleted(true);
        bookRepository.save(existingBook);
    }
}
