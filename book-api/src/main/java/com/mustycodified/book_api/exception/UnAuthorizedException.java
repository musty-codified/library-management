package com.mustycodified.book_api.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UnAuthorizedException extends RuntimeException {
    private String message;
    private String httpStatus;

    public UnAuthorizedException(String message, HttpStatus httpStatus) {
        this.httpStatus = String.valueOf(httpStatus);
        this.message = message;

    }
}