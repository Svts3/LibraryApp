package com.example.libraryapp.repository;

import com.example.libraryapp.model.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {

    Optional<TicketType>findByTicketType(String ticketType);
}
