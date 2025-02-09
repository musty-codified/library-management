package com.mustycodified.book_api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadCredentialsException extends RuntimeException{
    public BadCredentialsException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    private final HttpStatus status;
}
