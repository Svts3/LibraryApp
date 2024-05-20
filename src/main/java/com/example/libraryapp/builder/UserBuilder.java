package com.example.libraryapp.builder;

import com.example.libraryapp.model.User;
import com.example.libraryapp.model.UserBorrow;
import com.example.libraryapp.model.UserCategory;
import com.example.libraryapp.model.UserReturn;

import java.util.Date;
import java.util.Set;

public interface UserBuilder extends GeneralBuilder<User>{

    UserBuilder setId(Long id);
    UserBuilder setFirstName(String firstName);

    UserBuilder setLastName(String lastName);

    UserBuilder setEmail(String email);

    UserBuilder setPhoneNumber(String phoneNumber);

    UserBuilder setCreationDate(Date date);
    UserBuilder setLastModifiedDate(Date date);

    UserBuilder setUserBorrows(Set<UserBorrow> userBorrows);

    UserBuilder setUserCategory(UserCategory userCategory);

    UserBuilder setUserReturn(Set<UserReturn> userReturn);

    UserBuilder setBalance(Double balance);
}
