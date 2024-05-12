package com.example.libraryapp.builder;

import com.example.libraryapp.model.User;
import com.example.libraryapp.model.UserBorrow;
import com.example.libraryapp.model.UserCategory;
import com.example.libraryapp.model.UserReturn;

import java.util.Date;
import java.util.Set;

public interface UserBuilder extends GeneralBuilder<User>{

    void setId(Long id);
    void setFirstName(String firstName);

    void setLastName(String lastName);

    void setEmail(String email);

    void setPhoneNumber(String phoneNumber);

    void setCreationDate(Date date);
    void setLastModifiedDate(Date date);

    void setUserBorrows(Set<UserBorrow> userBorrows);

    void setUserCategory(UserCategory userCategory);

    void setUserReturn(Set<UserReturn> userReturn);
}
