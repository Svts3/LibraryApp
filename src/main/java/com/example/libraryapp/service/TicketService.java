package com.example.libraryapp.service;

import com.example.libraryapp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketService extends GeneralService<Ticket, Long> {

    Ticket assignTicketToUserReturn(String ticketType, Double fine, Long userId, Long userReturnId);

    Ticket cancelTicketForUserReturn(Long ticketId, Long userId);

    Ticket payTicket(Long userId, Long ticketId);


}
