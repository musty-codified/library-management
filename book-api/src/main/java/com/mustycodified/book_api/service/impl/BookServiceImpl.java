package com.mustycodified.book_api.service.impl;

import com.mustycodified.book_api.dto.request.BookRequestDto;
import com.mustycodified.book_api.dto.response.ApiResponse;
import com.mustycodified.book_api.dto.response.BookResponseDto;
import com.mustycodified.book_api.entity.Book;
import com.mustycodified.book_api.repository.BookRepository;
import com.mustycodified.book_api.service.BookService;
import com.mustycodified.book_api.util.CustomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        Page<Book> userPage = bookRepository.fetchAllBooks(searchText != null && !searchText.isEmpty() ? searchText : null, pageable);

        if (userPage.isEmpty()) {
            throw new RuntimeException("No Book Record was found");
        }
        List<BookResponseDto> responses = userPage.getContent().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());

        return new ApiResponse.Wrapper<>(
                responses,
                userPage.getTotalPages(),
                userPage.getTotalElements(),
                userPage.getNumberOfElements(),
                userPage.getNumber() + 1,
                userPage.isLast(),
                userPage.isFirst(),
                userPage.isEmpty()
        );
    }

    @Override
    public List<BookResponseDto> getBooks() {
        return List.of();
    }

    @Override
    public BookResponseDto editBook(Long id, BookRequestDto bookRequest) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (bookRequest.getAuthor() != null && !bookRequest.getAuthor().isEmpty()) {
            existingBook.setAuthor(bookRequest.getAuthor());
        }
        if (bookRequest.getTitle() != null) {
            existingBook.setTitle(bookRequest.getTitle());
        }

        if (bookRequest.getIsbn() != null) {
            existingBook.setTitle(bookRequest.getIsbn());
        }
        if (bookRequest.getQuantity() != null) {
            existingBook.setQuantity(bookRequest.getQuantity());
        }

        if (bookRequest.getPrice() != null) {
            existingBook.setPrice(bookRequest.getPrice());
        }
        existingBook.setUpdatedAt(LocalDateTime.now());
        Book savedBook = bookRepository.save(existingBook);

        return mapper.mapToDto(savedBook);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        if (!book.getBorrowedBooks().isEmpty())
            throw new RuntimeException("Book deletion failed as book is currently borrowed");
        bookRepository.delete(book);
    }
}

