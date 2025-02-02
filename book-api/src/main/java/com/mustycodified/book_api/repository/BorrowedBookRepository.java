package com.mustycodified.book_api.repository;

import com.mustycodified.book_api.entity.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {
    List<BorrowedBook> findByBookId(Long bookId);
}
