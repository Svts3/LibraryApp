package com.example.libraryapp.exception;

public class UserBalanceOperationException extends RuntimeException {
    public UserBalanceOperationException(String message) {
        super(message);
    }
}
