package com.example.libraryapp.service;

import com.example.libraryapp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketService extends GeneralService<Ticket, Long> {

    Ticket assignTicketToUser(String ticketType, Double fine, Long userId, Long bookId);

    Ticket cancelTicketForUser(Long ticketId, Long userId);

    Ticket payTicket(Long userId, Long ticketId);


}
