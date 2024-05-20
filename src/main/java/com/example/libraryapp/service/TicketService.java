package com.example.libraryapp.service;

import com.example.libraryapp.model.Ticket;
import com.example.libraryapp.model.UserReturn;

public interface TicketService extends
        Findable<Ticket, Long>, Persistable<Ticket, Long>, Updatable<Ticket, Long> {

    UserReturn assignTicketToUserReturn(String ticketType, Double fine, Long userId, Long userReturnId);

    UserReturn revokeTicketForUserReturn(Long ticketId, Long userId);

    Ticket payTicket(Long userId, Long ticketId);


}
