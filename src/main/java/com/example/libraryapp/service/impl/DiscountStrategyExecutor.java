package com.example.libraryapp.service.impl;

import com.example.libraryapp.model.Book;
import com.example.libraryapp.service.DiscountStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DiscountStrategyExecutor {

    private DiscountStrategy discountStrategy;

    public Double executeStrategy(Book book) {
        return discountStrategy.calculateDiscount(book);
    }
}
