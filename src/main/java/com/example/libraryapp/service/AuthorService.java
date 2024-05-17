package com.example.libraryapp.service;

import com.example.libraryapp.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService extends GeneralService<Author, Long>{

    List<Author> findByBooksId(Long bookId);

    Author findByFirstNameAndLastName(String firstName, String lastName);
}
