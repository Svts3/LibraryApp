package com.example.libraryapp.service;

import com.example.libraryapp.model.User;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserService extends GeneralService<User, Long>{

    User findByEmail(String email);

    List<User> findByUserCategory(String userCategory);

    User findByBookId(Long bookId);


}
