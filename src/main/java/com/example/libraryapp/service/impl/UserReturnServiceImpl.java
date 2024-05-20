package com.example.libraryapp.service.impl;

import com.example.libraryapp.exception.UserReturnNotFoundException;
import com.example.libraryapp.model.UserReturn;
import com.example.libraryapp.repository.UserReturnRepository;
import com.example.libraryapp.service.UserReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReturnServiceImpl implements UserReturnService {


    private UserReturnRepository userReturnRepository;

    @Autowired
    public UserReturnServiceImpl(UserReturnRepository userReturnRepository) {
        this.userReturnRepository = userReturnRepository;
    }

    @Override
    public List<UserReturn> findAll() {
        return userReturnRepository.findAll();
    }

    @Override
    public UserReturn findById(Long aLong) {
        return userReturnRepository.findById(aLong).orElseThrow(
                () -> new UserReturnNotFoundException(String.format("UserReturn[ID=%d] was not found!",
                        aLong)));
    }

    @Override
    public UserReturn save(UserReturn entity) {
        return userReturnRepository.save(entity);
    }

    @Override
    public UserReturn deleteById(Long aLong) {
        UserReturn userReturn = this.findById(aLong);
        userReturnRepository.deleteById(aLong);
        return userReturn;
    }

    @Override
    public List<UserReturn> findByUserId(Long userId) {
        return userReturnRepository.findByUserId(userId);
    }

    @Override
    public List<UserReturn> findByBookId(Long bookId) {
        return userReturnRepository.findByBookId(bookId);
    }
}
