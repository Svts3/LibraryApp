package com.example.libraryapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public enum BookStatus {

    IS_AVAILABLE("is_available"), IS_NOT_AVAILABLE("is_not_available"),
    IS_BORROWED("is_borrowed");

    private String statusName;

    BookStatus(String statusName) {
        this.statusName = statusName;
    }
}
