package com.example.libraryapp.service;

import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.User;

public interface DiscountCalculator {

    Double calculateDiscount(Book book, User user);
}
