package com.example.libraryapp.exception;

public class UserBorrowNotFoundException extends RuntimeException {
    public UserBorrowNotFoundException(String message) {
        super(message);
    }
}
