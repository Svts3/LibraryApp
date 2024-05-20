package com.example.libraryapp.builder.impl;

import com.example.libraryapp.builder.TicketBuilder;
import com.example.libraryapp.model.Ticket;
import com.example.libraryapp.model.TicketType;
import com.example.libraryapp.model.UserReturn;

public class TicketBuilderImpl implements TicketBuilder {

    private Ticket ticket;

    private TicketBuilderImpl(Ticket ticket) {
        this.ticket = ticket;
    }

    public TicketBuilderImpl() {
        ticket = new Ticket();
    }

    @Override
    public Ticket build() {
        return this.ticket;
    }

    @Override
    public TicketBuilder setId(Long id) {
        this.ticket.setId(id);
        return new TicketBuilderImpl(ticket);
    }

    @Override
    public TicketBuilder setFine(Double fine) {
        this.ticket.setFine(fine);
        return new TicketBuilderImpl(ticket);
    }

    @Override
    public TicketBuilder setTicketType(TicketType ticketType) {
        this.ticket.setTicketType(ticketType);
        return new TicketBuilderImpl(ticket);
    }

    @Override
    public TicketBuilder setIsPaid(Boolean isPaid) {
        this.ticket.setIsPaid(isPaid);
        return new TicketBuilderImpl(ticket);
    }

    @Override
    public TicketBuilder setUserReturn(UserReturn userReturn) {
        this.ticket.setUserReturn(userReturn);
        return new TicketBuilderImpl(ticket);
    }
}
