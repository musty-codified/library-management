package com.mustycodified.book_api.service.impl;

import com.mustycodified.book_api.dto.request.BookRequestDto;
import com.mustycodified.book_api.dto.response.ApiResponse;
import com.mustycodified.book_api.dto.response.BookResponseDto;
import com.mustycodified.book_api.entity.Book;
import com.mustycodified.book_api.repository.BookRepository;
import com.mustycodified.book_api.service.BookService;
import com.mustycodified.book_api.util.CustomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CustomMapper mapper;

    @Override
    public BookResponseDto addBook(BookRequestDto bookRequest) {
        if (bookRepository.existsByIsbn(bookRequest.getIsbn()))
            throw new RuntimeException("Book already exists");

        Book bookEntity = Book.builder()
                .isbn(bookRequest.getIsbn())
                .quantity(bookRequest.getQuantity())
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .price(bookRequest.getPrice())
                .publishedDate(LocalDateTime.now())
                .build();
        Book newBook = bookRepository.save(bookEntity);
        return mapper.mapToDto(newBook);
    }

    @Override
    public ApiResponse.Wrapper<List<BookResponseDto>> getAllBooks(String searchText, Pageable pageable) {

        return null;
    }

    @Override
    public List<BookResponseDto> getBooks() {
        return List.of();
    }

    @Override
    public BookResponseDto editBook(Long id, BookRequestDto bookRequest) {

        return null;
    }

    @Override
    public void deleteBook(Long id) {


    }

    @Override
    public BookResponseDto borrowBook(Long bookId, String email) {

        return null;
    }

    @Override
    public BookResponseDto returnBook(Long borrowedBookId) {

        return null;
    }
}
