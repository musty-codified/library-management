package com.mustycodified.book_api.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookNotFoundException extends RuntimeException {
    private String message;
    private String httpStatus;

    public BookNotFoundException(String message, String httpStatus) {
        super(message);
        this.httpStatus = String.valueOf(httpStatus);
        this.message = message;
    }
}
