package com.example.libraryapp.repository;

import com.example.libraryapp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t FROM Ticket t WHERE t.userReturn.user.id=:userId")
    List<Ticket>findByUserId(@Param("userId") Long userId);

    @Query("SELECT t FROM Ticket t WHERE t.userReturn.book.id=:bookId")
    List<Ticket>findByBookId(@Param("bookId")Long bookId);

    @Query("SELECT t FROM Ticket t WHERE t.fine > :min and t.fine < :max")
    List<Ticket>findByFineInRange(@Param("min")Double min, @Param("max") Double max);

}
