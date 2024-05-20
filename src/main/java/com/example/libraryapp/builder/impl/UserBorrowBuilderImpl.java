package com.example.libraryapp.builder.impl;

import com.example.libraryapp.builder.UserBorrowBuilder;
import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.User;
import com.example.libraryapp.model.UserBorrow;

import java.util.Date;

public class UserBorrowBuilderImpl implements UserBorrowBuilder {

    private UserBorrow userBorrow;

    private UserBorrowBuilderImpl(UserBorrow userBorrow) {
        this.userBorrow = userBorrow;
    }

    public UserBorrowBuilderImpl() {
        userBorrow = new UserBorrow();
    }

    @Override
    public UserBorrow build() {
        return this.userBorrow;
    }

    @Override
    public UserBorrowBuilder setId(Long id) {
        this.userBorrow.setId(id);
        return new UserBorrowBuilderImpl(userBorrow);
    }

    @Override
    public UserBorrowBuilder setIssueDate(Date issueDate) {
        this.userBorrow.setIssueDate(issueDate);
        return new UserBorrowBuilderImpl(userBorrow);
    }

    @Override
    public UserBorrowBuilder setDeadlineDate(Date deadlineDate) {
        this.userBorrow.setDeadlineDate(deadlineDate);
        return new UserBorrowBuilderImpl(userBorrow);
    }

    @Override
    public UserBorrowBuilder setUser(User user) {
        this.userBorrow.setUser(user);
        return new UserBorrowBuilderImpl(userBorrow);
    }

    @Override
    public UserBorrowBuilder setBook(Book book) {
        this.userBorrow.setBook(book);
        return new UserBorrowBuilderImpl(userBorrow);
    }

}
