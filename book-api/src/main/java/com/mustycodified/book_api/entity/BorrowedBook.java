package com.mustycodified.book_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "borrowed_books")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BorrowedBook extends Base{
    private String borrowedBookStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToOne(fetch = FetchType.LAZY)
    Transaction transaction;


}
