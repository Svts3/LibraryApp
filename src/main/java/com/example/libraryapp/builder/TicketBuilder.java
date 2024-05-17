package com.example.libraryapp.builder;

import com.example.libraryapp.model.Ticket;
import com.example.libraryapp.model.TicketType;
import com.example.libraryapp.model.UserReturn;

public interface TicketBuilder extends GeneralBuilder<Ticket>{

    void setId(Long id);

    void setFine(Double fine);

    void setTicketType(TicketType ticketType);

    void setIsPaid(Boolean isPaid);

    void setUserReturn(UserReturn userReturn);
}
