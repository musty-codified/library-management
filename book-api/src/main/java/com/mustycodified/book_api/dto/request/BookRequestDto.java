package com.mustycodified.book_api.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookRequestDto implements Serializable {

    private static final long serialVersionUID= 1L;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @NotBlank(message = "ISBN is required")
    @Size(min = 10, max = 13, message = "ISBN must be 10 or 13 characters long")
    private String isbn;

    @Min(1)
    @Max(100)
    private Integer quantity;

    @NotNull(message = "Price is required")
    private BigDecimal price;

}
