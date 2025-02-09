package com.mustycodified.book_api.service.impl;

import com.mustycodified.book_api.dto.request.BookRequestDto;
import com.mustycodified.book_api.dto.response.ApiResponse;
import com.mustycodified.book_api.dto.response.BookResponseDto;
import com.mustycodified.book_api.dto.response.BorrowedBookRespDto;
import com.mustycodified.book_api.entity.Book;
import com.mustycodified.book_api.entity.BorrowedBook;
import com.mustycodified.book_api.exception.BookNotFoundException;
import com.mustycodified.book_api.exception.OutOfStockException;
import com.mustycodified.book_api.exception.ResourceAlreadyExistException;
import com.mustycodified.book_api.repository.BookRepository;
import com.mustycodified.book_api.repository.BorrowedBookRepository;
import com.mustycodified.book_api.service.BookService;
import com.mustycodified.book_api.service.TransactionService;
import com.mustycodified.book_api.util.CustomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CustomMapper mapper;
    private final TransactionService transactionService;
    private final BorrowedBookRepository borrowedBookRepository;

    @Override
    public BookResponseDto addBook(BookRequestDto bookRequest) {
        if (bookRepository.existsByIsbn(bookRequest.getIsbn()))
            throw new ResourceAlreadyExistException("Book already exists");

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
        Page<Book> bookPage = bookRepository.fetchAllBooks(searchText != null && !searchText.isEmpty() ? searchText : null, pageable);

        if (bookPage.isEmpty()) {
            throw new BookNotFoundException("No Book Record was found", HttpStatus.NOT_FOUND.toString());
        }
        List<BookResponseDto> responses = bookPage.getContent().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());

        return new ApiResponse.Wrapper<>(
                responses,
                bookPage.getTotalPages(),
                bookPage.getTotalElements(),
                bookPage.getNumberOfElements(),
                bookPage.getNumber() + 1,
                bookPage.isLast(),
                bookPage.isFirst(),
                bookPage.isEmpty()
        );
    }

    @Override
    public List<BookResponseDto> getBooks() {
        return bookRepository.findAll().stream().map(mapper::mapToDto).toList();
    }

    @Override
    public BookResponseDto editBook(Long id, BookRequestDto bookRequest) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("No Book Record was found", HttpStatus.NOT_FOUND.toString()));

        if (bookRequest.getAuthor() != null && !bookRequest.getAuthor().isEmpty()) {
            existingBook.setAuthor(bookRequest.getAuthor());
        }
        if (bookRequest.getTitle() != null) {
            existingBook.setTitle(bookRequest.getTitle());
        }

        if (bookRequest.getIsbn() != null) {
            existingBook.setIsbn(bookRequest.getIsbn());
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
                .orElseThrow(() -> new BookNotFoundException("Book not found", HttpStatus.NOT_FOUND.toString()));
        if (!book.getBorrowedBooks().isEmpty())
            throw new RuntimeException("Book deletion failed as book is currently borrowed");
        bookRepository.delete(book);
    }

    @Override
    public BookResponseDto borrowBook(Long bookId, String email) {
        Book bookEntity = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found", HttpStatus.NOT_FOUND.toString()));

        if (bookEntity.getQuantity() <= 0)
            throw new OutOfStockException("Book is not in the shelf at the moment");
        BorrowedBookRespDto borrowedBookRespDto = transactionService.createBorrowedBook(email, bookEntity);
        BorrowedBook borrowedBook = mapper.mapToEntity(borrowedBookRespDto);
        borrowedBookRepository.save(borrowedBook);
        bookEntity.setQuantity(bookEntity.getQuantity() - 1);
        bookEntity.getBorrowedBooks().add(borrowedBook);
        Book updatedBookEntity = bookRepository.save(bookEntity);

        return mapper.mapToDto(updatedBookEntity);
    }

    @Override
    public BookResponseDto returnBook(Long borrowedBookId) {
        BorrowedBook borrowedBook = borrowedBookRepository.findById(borrowedBookId)
                .orElseThrow(() -> new BookNotFoundException("Borrowed book not found", HttpStatus.NOT_FOUND.toString()));
        Book bookEntity = borrowedBook.getBook();
        bookEntity.setQuantity(bookEntity.getQuantity() + 1);
        Book returnedBook = bookRepository.save(bookEntity);

        borrowedBookRepository.delete(borrowedBook);
        return mapper.mapToDto(returnedBook);
    }
}

