package com.colak.springtesttutorial.repository;

import com.colak.springtesttutorial.jpa.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
