package com.example.libraryapp.exception;

public class BookReturnException extends RuntimeException {
    public BookReturnException(String message) {
        super(message);
    }
}
