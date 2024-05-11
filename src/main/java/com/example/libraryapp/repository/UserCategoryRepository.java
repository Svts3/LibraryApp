package com.example.libraryapp.repository;

import com.example.libraryapp.model.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {

    Optional<UserCategory>findByCategoryName(String categoryName);
}
