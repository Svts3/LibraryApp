package com.example.libraryapp.service;

import com.example.libraryapp.model.Ticket;
import com.example.libraryapp.model.UserReturn;

public interface TicketService extends
        Findable<Ticket, Long>, Persistable<Ticket, Long>, Updatable<Ticket, Long> {

    Ticket assignTicketToUserReturn(Ticket ticket, Long userReturnId);

    Ticket revokeTicketForUserReturn(Long ticketId);

    Ticket payTicket(Long userId, Long ticketId);


}
