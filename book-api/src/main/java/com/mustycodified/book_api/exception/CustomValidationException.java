package com.mustycodified.book_api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class CustomValidationException extends RuntimeException{
    public CustomValidationException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    private final HttpStatus status;
}
