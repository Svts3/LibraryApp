package com.example.libraryapp.builder;

import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.User;
import com.example.libraryapp.model.UserBorrow;

import java.util.Date;

public interface UserBorrowBuilder extends GeneralBuilder<UserBorrow>{

    UserBorrowBuilder setId(Long id);

    UserBorrowBuilder setIssueDate(Date issueDate);

    UserBorrowBuilder setDeadlineDate(Date deadlineDate);

    UserBorrowBuilder setUser(User user);

    UserBorrowBuilder setBook(Book book);


}
