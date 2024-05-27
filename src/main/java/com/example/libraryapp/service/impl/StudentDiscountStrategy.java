package com.example.libraryapp.service.impl;

import com.example.libraryapp.model.Book;
import com.example.libraryapp.service.DiscountStrategy;

public class StudentDiscountStrategy implements DiscountStrategy {

    @Override
    public Double calculateDiscount(Book book) {
        return book.getSecurityDeposit()-(book.getSecurityDeposit() * 0.05);
    }
}
