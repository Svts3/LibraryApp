package com.example.libraryapp.service;

import com.example.libraryapp.dto.UserReturnRequest;
import com.example.libraryapp.model.UserReturn;

import java.util.List;

public interface UserReturnService extends Findable<UserReturn, Long>, Persistable<UserReturn, Long>{

    UserReturn returnBook(UserReturnRequest userReturnRequest);
    List<UserReturn> findByUserId(Long userId);

    List<UserReturn>findByBookId(Long bookId);
}
