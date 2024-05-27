package com.example.libraryapp.service.strategy.impl;

import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.User;
import com.example.libraryapp.service.DiscountCalculator;
import org.springframework.stereotype.Service;

@Service
public class DiscountCalculatorImpl implements DiscountCalculator {

    @Override
    public Double calculateDiscount(Book book, User user) {

        DiscountStrategyExecutor discountStrategyExecutor;

        String userCategoryName = user.getUserCategory().getCategoryName();

        if (userCategoryName.equalsIgnoreCase("STUDENT")) {
            discountStrategyExecutor = new DiscountStrategyExecutor(new StudentDiscountStrategy());
        } else if (userCategoryName.equalsIgnoreCase("SENIOR")) {
            discountStrategyExecutor = new DiscountStrategyExecutor(new SeniorDiscountStrategy());
        } else if (userCategoryName.equalsIgnoreCase("FREQUENT_BORROWER")) {
            discountStrategyExecutor = new DiscountStrategyExecutor(new FrequentBorrowerDiscountStrategy());
        } else {
            discountStrategyExecutor = new DiscountStrategyExecutor(new CustomerDiscountStrategy());
        }
        return discountStrategyExecutor.executeStrategy(book);
    }
}
