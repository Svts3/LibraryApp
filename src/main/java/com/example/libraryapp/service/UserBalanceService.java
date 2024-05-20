package com.example.libraryapp.service;

import com.example.libraryapp.model.User;

public interface UserBalanceService {

    User depositToUserBalance(Long userId, Double amount);

    User withdrawFromUserBalance(Long userId, Double amount);


}
