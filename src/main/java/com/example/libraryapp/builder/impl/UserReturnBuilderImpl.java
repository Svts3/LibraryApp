package com.example.libraryapp.builder.impl;

import com.example.libraryapp.builder.UserReturnBuilder;
import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.Ticket;
import com.example.libraryapp.model.User;
import com.example.libraryapp.model.UserReturn;

import java.util.Date;
import java.util.Set;

public class UserReturnBuilderImpl implements UserReturnBuilder {

    private UserReturn userReturn;

    private UserReturnBuilderImpl(UserReturn userReturn) {
        this.userReturn = userReturn;
    }

    public UserReturnBuilderImpl() {
        userReturn = new UserReturn();
    }

    @Override
    public UserReturn build() {
        return this.userReturn;
    }

    @Override
    public UserReturnBuilder setId(Long id) {
        this.userReturn.setId(id);
        return new UserReturnBuilderImpl(userReturn);
    }

    @Override
    public UserReturnBuilder setReturnDate(Date returnDate) {
        this.userReturn.setReturnDate(returnDate);
        return new UserReturnBuilderImpl(userReturn);
    }

    @Override
    public UserReturnBuilder setBook(Book book) {
        this.userReturn.setBook(book);
        return new UserReturnBuilderImpl(userReturn);
    }

    @Override
    public UserReturnBuilder setTickets(Set<Ticket> tickets) {
        this.userReturn.setTickets(tickets);
        return new UserReturnBuilderImpl(userReturn);
    }

    @Override
    public UserReturnBuilder setUser(User user) {
        this.userReturn.setUser(user);
        return new UserReturnBuilderImpl(userReturn);
    }
}
