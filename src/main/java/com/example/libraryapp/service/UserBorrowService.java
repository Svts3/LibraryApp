package com.example.libraryapp.service;

import com.example.libraryapp.model.UserBorrow;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface UserBorrowService extends
        Findable<UserBorrow, Long>, Persistable<UserBorrow, Long>{

    List<UserBorrow> findByUserId(Long userId);

    List<UserBorrow>findByBookId(Long bookId);

    @Query("SELECT ub FROM UserBorrow ub WHERE ub.user.id=:userId and ub.deadlineDate > CURRENT_DATE")
    List<UserBorrow>findOverdueBorrowsByUserId(@Param("userId") Long userId);
    @Query("SELECT ub FROM UserBorrow ub WHERE ub.user.id=:userId and ub.deadlineDate < CURRENT_DATE")
    List<UserBorrow>findEarlyBorrowsByUserId(@Param("userId")Long userId);
    @Query("SELECT ub FROM UserBorrow ub WHERE ub.issueDate > :min and ub.issueDate < :max")
    List<UserBorrow>findByIssueIdInRange(@Param("min") Date min, @Param("max")Date max);
}
