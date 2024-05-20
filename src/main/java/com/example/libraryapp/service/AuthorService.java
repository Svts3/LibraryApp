package com.example.libraryapp.service;

import com.example.libraryapp.model.Author;

import java.util.List;

public interface AuthorService extends
        Findable<Author, Long>, Persistable<Author, Long>, Updatable<Author, Long> {

    List<Author> findByBooksId(Long bookId);

    Author findByFirstNameAndLastName(String firstName, String lastName);
}
