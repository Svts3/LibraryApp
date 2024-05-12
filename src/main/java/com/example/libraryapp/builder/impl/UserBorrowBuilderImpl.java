package com.example.libraryapp.builder.impl;

import com.example.libraryapp.builder.UserBorrowBuilder;
import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.User;
import com.example.libraryapp.model.UserBorrow;

import java.util.Date;

public class UserBorrowBuilderImpl implements UserBorrowBuilder {

    private UserBorrow userBorrow;

    public UserBorrowBuilderImpl(UserBorrow userBorrow) {
        this.userBorrow = userBorrow;
    }

    @Override
    public UserBorrow build() {
        return this.userBorrow;
    }

    @Override
    public void setId(Long id) {
        this.userBorrow.setId(id);
    }

    @Override
    public void setIssueDate(Date issueDate) {
        this.userBorrow.setIssueDate(issueDate);
    }

    @Override
    public void setDeadlineDate(Date deadlineDate) {
        this.userBorrow.setDeadlineDate(deadlineDate);
    }

    @Override
    public void setUser(User user) {
        this.userBorrow.setUser(user);
    }

    @Override
    public void setBook(Book book) {
        this.userBorrow.setBook(book);
    }
}
