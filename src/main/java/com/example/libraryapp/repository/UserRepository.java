package com.example.libraryapp.repository;

import com.example.libraryapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User>findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.userCategory.categoryName=:userCategory")
    List<User> findByUserCategory(@Param("userCategory")String userCategory);

    @Query("SELECT u FROM User u JOIN UserBorrow ub on ub.user.id = u.id and ub.book.id=:bookId")
    Optional<User>findByBookId(@Param("bookId")Long bookId);



}
