package com.example.libraryapp.builder.impl;

import com.example.libraryapp.builder.TicketBuilder;
import com.example.libraryapp.model.Ticket;
import com.example.libraryapp.model.TicketType;
import com.example.libraryapp.model.UserReturn;

public class TicketBuilderImpl implements TicketBuilder {

    private Ticket ticket;


    @Override
    public Ticket build() {
        return this.ticket;
    }

    @Override
    public void setId(Long id) {
        this.ticket.setId(id);
    }

    @Override
    public void setFine(Double fine) {
        this.ticket.setFine(fine);
    }

    @Override
    public void setTicketType(TicketType ticketType) {
        this.ticket.setTicketType(ticketType);
    }

    @Override
    public void setIsPaid(Boolean isPaid) {
        this.ticket.setIsPaid(isPaid);
    }

    @Override
    public void setUserReturn(UserReturn userReturn) {
        this.ticket.setUserReturn(userReturn);
    }
}
