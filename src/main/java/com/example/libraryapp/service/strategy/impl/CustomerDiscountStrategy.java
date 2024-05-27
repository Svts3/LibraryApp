package com.example.libraryapp.service.strategy.impl;

import com.example.libraryapp.model.Book;
import com.example.libraryapp.service.strategy.DiscountStrategy;

public class CustomerDiscountStrategy implements DiscountStrategy {

    @Override
    public Double calculateDiscount(Book book) {
        return book.getSecurityDeposit();
    }
}
