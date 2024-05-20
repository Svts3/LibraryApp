package com.example.libraryapp.builder;

import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.Ticket;
import com.example.libraryapp.model.User;
import com.example.libraryapp.model.UserReturn;

import java.util.Date;
import java.util.Set;

public interface UserReturnBuilder extends GeneralBuilder<UserReturn>{

    UserReturnBuilder setId(Long id);

    UserReturnBuilder setReturnDate(Date returnDate);

    UserReturnBuilder setBook(Book book);

    UserReturnBuilder setTickets(Set<Ticket> tickets);

    UserReturnBuilder setUser(User user);

}
