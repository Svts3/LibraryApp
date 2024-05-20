package com.example.libraryapp.repository;

import com.example.libraryapp.model.UserReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserReturnRepository extends JpaRepository<UserReturn, Long> {

    List<UserReturn>findByUserId(Long userId);

    List<UserReturn>findByBookId(Long bookId);
}
