package com.example.libraryapp.builder.impl;

import com.example.libraryapp.builder.UserBuilder;
import com.example.libraryapp.model.User;
import com.example.libraryapp.model.UserBorrow;
import com.example.libraryapp.model.UserCategory;
import com.example.libraryapp.model.UserReturn;

import java.util.Date;
import java.util.Set;

public class UserBuilderImpl implements UserBuilder {

    private User user;

    private UserBuilderImpl(User user) {
        this.user = user;
    }

    public UserBuilderImpl() {
        user = new User();
    }

    @Override
    public UserBuilder setId(Long id) {
        this.user.setId(id);
        return new UserBuilderImpl(user);
    }

    @Override
    public UserBuilder setFirstName(String firstName) {
        this.user.setFirstName(firstName);
        return new UserBuilderImpl(user);
    }

    @Override
    public UserBuilder setLastName(String lastName) {
        this.user.setLastName(lastName);
        return new UserBuilderImpl(user);
    }

    @Override
    public UserBuilder setEmail(String email) {
        this.user.setEmail(email);
        return new UserBuilderImpl(user);
    }

    @Override
    public UserBuilder setPhoneNumber(String phoneNumber) {
        this.user.setPhoneNumber(phoneNumber);
        return new UserBuilderImpl(user);
    }

    @Override
    public UserBuilder setCreationDate(Date date) {
        this.user.setCreationDate(date);
        return new UserBuilderImpl(user);
    }

    @Override
    public UserBuilder setLastModifiedDate(Date date) {
        this.user.setLastModifiedDate(date);
        return new UserBuilderImpl(user);
    }

    @Override
    public UserBuilder setUserBorrows(Set<UserBorrow> userBorrows) {
        this.user.setUserBorrows(userBorrows);
        return new UserBuilderImpl(user);
    }

    @Override
    public UserBuilder setUserCategory(UserCategory userCategory) {
        this.user.setUserCategory(userCategory);
        return new UserBuilderImpl(user);
    }

    @Override
    public UserBuilder setUserReturn(Set<UserReturn> userReturns) {
        this.user.setUserReturns(userReturns);
        return new UserBuilderImpl(user);
    }

    @Override
    public UserBuilder setBalance(Double balance) {
        this.user.setBalance(balance);
        return new UserBuilderImpl(user);
    }

    @Override
    public User build() {
        return this.user;
    }
}
