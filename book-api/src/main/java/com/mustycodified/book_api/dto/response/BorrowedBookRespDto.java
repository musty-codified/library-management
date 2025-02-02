package com.mustycodified.book_api.dto.response;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class BorrowedBookRespDto implements Serializable {
    private static final long serialVersionUID= 1L;
    private String borrowerEmail;
    private BigDecimal price;
    private String isbn;

}
