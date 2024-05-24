package com.example.libraryapp.service;

import com.example.libraryapp.model.Ticket;
import com.example.libraryapp.model.UserReturn;

public interface TicketService extends
        Findable<Ticket, Long>, Persistable<Ticket, Long>, Updatable<Ticket, Long> {

    UserReturn assignTicketToUserReturn(Ticket ticket, Long userReturnId);

    UserReturn revokeTicketForUserReturn(Long ticketId, Long userReturnId);

    Ticket payTicket(Long userId, Long ticketId);


}
