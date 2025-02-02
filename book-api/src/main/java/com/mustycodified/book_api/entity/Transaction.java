package com.mustycodified.book_api.entity;

import com.mustycodified.book_api.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction extends Base{

    private String reference;

    private String email;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @OneToOne
    private BorrowedBook borrowedBook;
}
