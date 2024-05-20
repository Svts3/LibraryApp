package com.example.libraryapp.service.impl;

import com.example.libraryapp.exception.UserBorrowNotFoundException;
import com.example.libraryapp.mapper.UserMapper;
import com.example.libraryapp.model.UserBorrow;
import com.example.libraryapp.repository.UserBorrowRepository;
import com.example.libraryapp.service.UserBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserBorrowServiceImpl implements UserBorrowService {

    private UserBorrowRepository userBorrowRepository;

    private UserMapper userMapper;

    @Autowired
    public UserBorrowServiceImpl(UserBorrowRepository userBorrowRepository) {
        this.userBorrowRepository = userBorrowRepository;
    }

    @Override
    public List<UserBorrow> findAll() {
        return userBorrowRepository.findAll();
    }

    @Override
    public UserBorrow findById(Long aLong) {
        return userBorrowRepository.findById(aLong).orElseThrow(
                ()->new UserBorrowNotFoundException(String.format("UserBorrow[ID=%d] was not found!",
                        aLong)));
    }

    @Override
    public UserBorrow save(UserBorrow entity) {
        return userBorrowRepository.save(entity);
    }

    @Override
    public UserBorrow deleteById(Long aLong) {
        UserBorrow userBorrow = this.findById(aLong);
        userBorrowRepository.deleteById(aLong);
        return userBorrow;
    }

    @Override
    public List<UserBorrow> findByUserId(Long userId) {
        return userBorrowRepository.findByUserId(userId);
    }

    @Override
    public List<UserBorrow> findByBookId(Long bookId) {
        return userBorrowRepository.findByBookId(bookId);
    }

    @Override
    public List<UserBorrow> findOverdueBorrowsByUserId(Long userId) {
        return userBorrowRepository.findOverdueBorrowsByUserId(userId);
    }

    @Override
    public List<UserBorrow> findEarlyBorrowsByUserId(Long userId) {
        return userBorrowRepository.findEarlyBorrowsByUserId(userId);
    }

    @Override
    public List<UserBorrow> findByIssueIdInRange(Date min, Date max) {
        return userBorrowRepository.findByIssueIdInRange(min, max);
    }
}
