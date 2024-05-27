package com.example.libraryapp.service.impl;

import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.UserCategory;
import com.example.libraryapp.service.DiscountStrategy;

public class CustomerDiscountStrategy implements DiscountStrategy {

    @Override
    public Double calculateDiscount(Book book) {
        return book.getSecurityDeposit();
    }
}
