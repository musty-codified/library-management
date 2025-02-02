package com.mustycodified.book_api.service;

import com.mustycodified.book_api.dto.response.BorrowedBookRespDto;
import com.mustycodified.book_api.entity.Book;

public interface TransactionService {
  BorrowedBookRespDto createBorrowedBook(String email, Book book);

}
