package com.mustycodified.book_api.repository;

import com.mustycodified.book_api.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction>findByEmail(String email);
}
