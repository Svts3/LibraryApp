package com.example.libraryapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public enum DiscountMeasure {

    PERCENTAGE("percentage"), FIXED_AMOUNT("fixed_amount");

    private String measureName;

    DiscountMeasure(String measureName) {
        this.measureName = measureName;
    }
}
