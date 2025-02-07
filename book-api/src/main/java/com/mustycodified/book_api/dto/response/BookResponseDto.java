package com.mustycodified.book_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookResponseDto implements Serializable {
    private Long id;
    private String title;
    private String author;
    private int quantity;
    private String isbn;
    private LocalDateTime publishedDate;

}
