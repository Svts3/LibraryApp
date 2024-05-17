package com.example.libraryapp.repository;

import com.example.libraryapp.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByBooksId(Long bookId);

    Optional<Author>findByFirstNameAndLastName(String firstName, String lastName);
}
