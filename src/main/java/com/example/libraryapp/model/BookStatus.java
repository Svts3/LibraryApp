package com.example.libraryapp.model;

import lombok.Getter;

@Getter
public enum BookStatus {

    IS_AVAILABLE("is_available"), IS_NOT_AVAILABLE("is_not_available"),
    IS_BORROWED("is_borrowed");

    private String statusName;

    BookStatus(String statusName) {
        this.statusName = statusName;
    }
}
