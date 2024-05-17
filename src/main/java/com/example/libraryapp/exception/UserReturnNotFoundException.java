package com.example.libraryapp.exception;

public class UserReturnNotFoundException extends RuntimeException {
    public UserReturnNotFoundException(String message) {
        super(message);
    }
}
