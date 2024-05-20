package com.example.libraryapp.model;

import lombok.Getter;

@Getter
public enum DiscountMeasure {

    PERCENTAGE("percentage"), FIXED_AMOUNT("fixed_amount");

    private String measureName;

    DiscountMeasure(String measureName) {
        this.measureName = measureName;
    }
}
