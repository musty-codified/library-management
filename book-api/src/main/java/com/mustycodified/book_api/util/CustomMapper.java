package com.mustycodified.book_api.util;

import com.mustycodified.book_api.dto.response.BookResponseDto;
import com.mustycodified.book_api.dto.response.BorrowedBookRespDto;
import com.mustycodified.book_api.dto.response.UserResponseDto;
import com.mustycodified.book_api.entity.Book;
import com.mustycodified.book_api.entity.BorrowedBook;
import com.mustycodified.book_api.entity.Transaction;
import com.mustycodified.book_api.entity.User;
import com.mustycodified.book_api.enums.BookStatus;
import com.mustycodified.book_api.repository.BookRepository;
import com.mustycodified.book_api.repository.TransactionRepository;
import com.mustycodified.book_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomMapper {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final TransactionRepository transactionRepository;

    public UserResponseDto mapToDto(User userEntity) {
        return UserResponseDto.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .phoneNumber(userEntity.getPhoneNumber())
                .build();
    }

    public BookResponseDto mapToDto(Book bookEntity) {
        return BookResponseDto.builder()
                .id(bookEntity.getId())
                .title(bookEntity.getTitle())
                .author(bookEntity.getAuthor())
                .quantity(bookEntity.getQuantity())
                .publishedDate(bookEntity.getPublishedDate())
                .isbn(bookEntity.getIsbn())
                .build();
    }

    public BorrowedBookRespDto mapToDto(BorrowedBook borrowedBookEntity) {
        return BorrowedBookRespDto.builder()
                .borrowerEmail(borrowedBookEntity.getUser().getEmail())
                .price(borrowedBookEntity.getBook().getPrice())
                .isbn(borrowedBookEntity.getBook().getIsbn())
                .build();
    }

    public BorrowedBook mapToEntity(BorrowedBookRespDto dto) {
        User user = userRepository.findByEmail(dto.getBorrowerEmail())
                .orElseThrow(() -> new RuntimeException("Not found"));
        Book book = bookRepository.findByIsbn(dto.getIsbn())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        Transaction transaction = transactionRepository.findByEmail(dto.getBorrowerEmail())
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        return BorrowedBook.builder()
                .user(user)
                .book(book)
                .borrowedBookStatus(String.valueOf(BookStatus.BORROWED))
                .transaction(transaction)
                .build();
    }

}
