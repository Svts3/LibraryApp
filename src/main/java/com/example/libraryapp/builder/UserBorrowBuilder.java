package com.example.libraryapp.builder;

import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.User;
import com.example.libraryapp.model.UserBorrow;

import java.util.Date;

public interface UserBorrowBuilder extends GeneralBuilder<UserBorrow>{

    void setId(Long id);

    void setIssueDate(Date issueDate);

    void setDeadlineDate(Date deadlineDate);

    void setUser(User user);

    void setBook(Book book);


}
