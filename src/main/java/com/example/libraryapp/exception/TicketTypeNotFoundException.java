package com.example.libraryapp.exception;

public class TicketTypeNotFoundException extends RuntimeException {
    public TicketTypeNotFoundException(String message) {
        super(message);
    }
}
