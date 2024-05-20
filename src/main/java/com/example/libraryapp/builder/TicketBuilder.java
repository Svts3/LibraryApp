package com.example.libraryapp.builder;

import com.example.libraryapp.model.Ticket;
import com.example.libraryapp.model.TicketType;
import com.example.libraryapp.model.UserReturn;

public interface TicketBuilder extends GeneralBuilder<Ticket>{

    TicketBuilder setId(Long id);

    TicketBuilder setFine(Double fine);

    TicketBuilder setTicketType(TicketType ticketType);

    TicketBuilder setIsPaid(Boolean isPaid);

    TicketBuilder setUserReturn(UserReturn userReturn);
}
