package com.mustycodified.book_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "books")
public class Book extends Base {

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 50)
    private String author;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private LocalDateTime publishedDate;

    @Column(nullable = false)
    private BigDecimal price;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private List<BorrowedBook> borrowedBooks;

}
