package com.example.libraryapp.builder;

import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.Ticket;
import com.example.libraryapp.model.User;
import com.example.libraryapp.model.UserReturn;

import java.util.Date;
import java.util.Set;

public interface UserReturnBuilder extends GeneralBuilder<UserReturn>{

    void setId(Long id);

    void setReturnDate(Date returnDate);

    void setBook(Book book);

    void setTickets(Set<Ticket> tickets);

    void setUser(User user);

}
