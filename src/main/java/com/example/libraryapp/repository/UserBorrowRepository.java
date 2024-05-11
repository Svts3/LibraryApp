package com.example.libraryapp.repository;

import com.example.libraryapp.model.UserBorrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserBorrowRepository extends JpaRepository<UserBorrow, Long> {

    Collection<UserBorrow> findByUserId(Long userId);

    Collection<UserBorrow>findByBookId(Long bookId);

    @Query("SELECT ub FROM UserBorrow ub WHERE ub.user.id=:userId and ub.deadlineDate > CURRENT_DATE")
    Collection<UserBorrow>findOverdueBorrowsByUserId(@Param("userId") Long userId);
    @Query("SELECT ub FROM UserBorrow ub WHERE ub.user.id=:userId and ub.deadlineDate < CURRENT_DATE")
    Collection<UserBorrow>findEarlyBorrowsByUserId(@Param("userId")Long userId);
    @Query("SELECT ub FROM UserBorrow ub WHERE ub.issueDate > :min and ub.issueDate < :max")
    Collection<UserBorrow>findByIssueIdInRange(@Param("min")Date min, @Param("max")Date max);


}

