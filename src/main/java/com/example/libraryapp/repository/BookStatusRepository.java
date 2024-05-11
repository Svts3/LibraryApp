package com.example.libraryapp.repository;

import com.example.libraryapp.model.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookStatusRepository extends JpaRepository<BookStatus, Long> {

    Optional<BookStatus>findByStatusName(String status);
}
