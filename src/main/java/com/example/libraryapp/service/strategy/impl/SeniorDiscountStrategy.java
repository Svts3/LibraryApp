package com.example.libraryapp.service.strategy.impl;

import com.example.libraryapp.model.Book;
import com.example.libraryapp.service.strategy.DiscountStrategy;

public class SeniorDiscountStrategy implements DiscountStrategy {

    @Override
    public Double calculateDiscount(Book book) {
        return book.getSecurityDeposit()-(book.getSecurityDeposit()*0.10);
    }
}
