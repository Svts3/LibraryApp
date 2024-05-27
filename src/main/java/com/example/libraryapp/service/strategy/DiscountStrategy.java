package com.example.libraryapp.service.strategy;

import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.UserCategory;

public interface DiscountStrategy {

    Double calculateDiscount(Book book);
}
