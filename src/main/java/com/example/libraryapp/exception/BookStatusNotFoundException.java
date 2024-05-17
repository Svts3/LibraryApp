package com.example.libraryapp.exception;

public class BookStatusNotFoundException extends RuntimeException {
    public BookStatusNotFoundException(String message) {
        super(message);
    }
}
