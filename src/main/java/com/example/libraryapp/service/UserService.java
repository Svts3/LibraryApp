package com.example.libraryapp.service;

import com.example.libraryapp.model.User;

import java.util.List;

public interface UserService extends
        Findable<User, Long>, Persistable<User, Long>, Updatable<User, Long>{

    User findByEmail(String email);

    List<User> findByUserCategory(String userCategory);

    User findByBookId(Long bookId);


}
