package com.mustycodified.book_api.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutOfStockException extends RuntimeException {
    private String message;

    public OutOfStockException(String message) {
        super(message);
        this.message = message;
    }
}
