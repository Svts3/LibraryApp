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

    public UserBuilderImpl(User user) {
        this.user = user;
    }

    @Override
    public void setId(Long id) {
        this.user.setId(id);
    }

    @Override
    public void setFirstName(String firstName) {
        this.user.setFirstName(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        this.user.setLastName(lastName);
    }

    @Override
    public void setEmail(String email) {
        this.user.setEmail(email);
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.user.setPhoneNumber(phoneNumber);
    }

    @Override
    public void setCreationDate(Date date) {
        this.user.setCreationDate(date);
    }

    @Override
    public void setLastModifiedDate(Date date) {
        this.user.setLastModifiedDate(date);
    }

    @Override
    public void setUserBorrows(Set<UserBorrow> userBorrows) {
        this.user.setUserBorrows(userBorrows);
    }

    @Override
    public void setUserCategory(UserCategory userCategory) {
        this.user.setUserCategory(userCategory);
    }

    @Override
    public void setUserReturn(Set<UserReturn> userReturns) {
            this.user.setUserReturns(userReturns);
    }

    @Override
    public User build() {
        return this.user;
    }
}
