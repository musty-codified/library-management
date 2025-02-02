package com.mustycodified.book_api.service.impl;

import com.mustycodified.book_api.dto.response.BorrowedBookRespDto;
import com.mustycodified.book_api.entity.Book;
import com.mustycodified.book_api.entity.BorrowedBook;
import com.mustycodified.book_api.entity.Transaction;
import com.mustycodified.book_api.entity.User;
import com.mustycodified.book_api.enums.BookStatus;
import com.mustycodified.book_api.enums.TransactionType;
import com.mustycodified.book_api.repository.TransactionRepository;
import com.mustycodified.book_api.repository.UserRepository;
import com.mustycodified.book_api.service.TransactionService;
import com.mustycodified.book_api.util.AppUtil;
import com.mustycodified.book_api.util.CustomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final AppUtil appUtil;
    private final CustomMapper mapper;
    @Override
    public BorrowedBookRespDto createBorrowedBook(String email, Book book) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found"));
      BigDecimal bookPrice = calculateCharge(book);
        Transaction transaction = transactionRepository.findByEmail(email)
                .orElse(Transaction.builder()
                        .reference(appUtil.generateSerialNumber("TR"))
                        .amount(bookPrice)
                        .transactionType(TransactionType.PAYMENT)
                        .email(email)
                        .build());

        BigDecimal balance = userRepository.findByEmail(email)
                .map(User::getWalletBalance).orElse(BigDecimal.ZERO);

        if (balance.compareTo(bookPrice) < 0)
            throw new RuntimeException("Insufficient fund");

        user.setWalletBalance(user.getWalletBalance().subtract(bookPrice));
        userRepository.save(user);
        transactionRepository.save(transaction);

        BorrowedBook borrowedBook = BorrowedBook.builder()
                .book(book)
                .user(user)
                .borrowedBookStatus(String.valueOf(BookStatus.BORROWED))
                .transaction(transaction)
                .build();
        return mapper.mapToDto(borrowedBook);
    }

    private BigDecimal calculateCharge(Book book) {
        return book.getPrice().divide(new BigDecimal(100), 2, RoundingMode.DOWN);
    }

}
