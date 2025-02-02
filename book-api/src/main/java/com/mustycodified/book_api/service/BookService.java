package com.mustycodified.book_api.service;

import com.mustycodified.book_api.dto.request.BookRequestDto;
import com.mustycodified.book_api.dto.response.ApiResponse;
import com.mustycodified.book_api.dto.response.BookResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    BookResponseDto addBook(BookRequestDto bookRequest);
    ApiResponse.Wrapper<List<BookResponseDto>> getAllBooks(String searchText, Pageable pageable);
    List<BookResponseDto>getBooks();
    BookResponseDto editBook(Long id, BookRequestDto bookRequest);
    void deleteBook(Long id);
    BookResponseDto borrowBook(Long id, String borrowerEmail);
    BookResponseDto returnBook(Long id);

}
