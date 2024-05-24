package com.example.libraryapp.service.impl;

import com.example.libraryapp.exception.UserBalanceOperationException;
import com.example.libraryapp.model.User;
import com.example.libraryapp.service.UserBalanceService;
import com.example.libraryapp.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBalanceServiceImpl implements UserBalanceService {

    private UserService userService;

    @Autowired
    public UserBalanceServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @Override
    public User depositToUserBalance(Long userId, Double amount) {
        User user = userService.findById(userId);
        user.setBalance(user.getBalance()+amount);
        return userService.save(user);
    }

    @Transactional
    @Override
    public User withdrawFromUserBalance(Long userId, Double amount) {
        User user = userService.findById(userId);
        if(user.getBalance() < amount){
            throw new UserBalanceOperationException("Transaction cannot be completed due to a low balance");
        }
        user.setBalance(user.getBalance()-amount);
        return userService.save(user);
    }
}
