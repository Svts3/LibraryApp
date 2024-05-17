package com.example.libraryapp.service.impl;

import com.example.libraryapp.exception.UserNotFoundException;
import com.example.libraryapp.mapper.UserMapper;
import com.example.libraryapp.model.User;
import com.example.libraryapp.repository.UserRepository;
import com.example.libraryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public User findById(Long aLong) {
        return userRepository.findById(aLong).orElseThrow(
                ()->new UserNotFoundException(String.format("User[ID=%d] was not found!", aLong)));
    }

    @Override
    public User update(User entity, Long aLong) {
        User user = findById(aLong);
        userMapper.updateUser(user, entity);
        return userRepository.save(user);
    }

    @Override
    public User deleteById(Long aLong) {
        User user = findById(aLong);
        userRepository.deleteById(aLong);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                ()->new UserNotFoundException(String.format("User[email=%s] was not found!", email)));
    }

    @Override
    public List<User> findByUserCategory(String userCategory) {
        return userRepository.findByUserCategory(userCategory);
    }

    @Override
    public User findByBookId(Long bookId) {
        return userRepository.findByBookId(bookId).orElseThrow(
                ()->new UserNotFoundException(String.format("User by Book[ID=%d] was not found!", bookId)));
    }
}
