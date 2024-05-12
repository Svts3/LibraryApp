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

    public UserReturnBuilderImpl(UserReturn userReturn) {
        this.userReturn = userReturn;
    }

    @Override
    public UserReturn build() {
        return this.userReturn;
    }

    @Override
    public void setId(Long id) {
        this.userReturn.setId(id);
    }

    @Override
    public void setReturnDate(Date returnDate) {
        this.userReturn.setReturnDate(returnDate);
    }

    @Override
    public void setBook(Book book) {
        this.userReturn.setBook(book);
    }

    @Override
    public void setTickets(Set<Ticket> tickets) {
        this.userReturn.setTickets(tickets);
    }

    @Override
    public void setUser(User user) {
        this.userReturn.setUser(user);
    }
}
