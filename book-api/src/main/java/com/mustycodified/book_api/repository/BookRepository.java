package com.mustycodified.book_api.repository;

import com.mustycodified.book_api.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Boolean existsByIsbn(String isbn);


}
