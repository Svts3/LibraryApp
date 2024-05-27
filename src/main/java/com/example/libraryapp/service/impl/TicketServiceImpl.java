package com.example.libraryapp.service.impl;

import com.example.libraryapp.builder.impl.TicketBuilderImpl;
import com.example.libraryapp.exception.TicketNotFoundException;
import com.example.libraryapp.exception.UserReturnNotFoundException;
import com.example.libraryapp.mapper.TicketMapper;
import com.example.libraryapp.model.Ticket;
import com.example.libraryapp.model.UserReturn;
import com.example.libraryapp.repository.TicketRepository;
import com.example.libraryapp.repository.UserReturnRepository;
import com.example.libraryapp.service.TicketService;
import com.example.libraryapp.service.UserBalanceService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;


    private UserReturnRepository userReturnRepository;

    private UserBalanceService userBalanceService;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository,
                             UserReturnRepository userReturnRepository,
                             UserBalanceService userBalanceService) {
        this.ticketRepository = ticketRepository;
        this.userReturnRepository = userReturnRepository;
        this.userBalanceService = userBalanceService;
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket save(Ticket entity) {
        return ticketRepository.save(entity);
    }

    @Override
    public Ticket findById(Long aLong) {
        return ticketRepository.findById(aLong).orElseThrow(
                () -> new TicketNotFoundException(String.format("Ticket[ID=%d] was not found!", aLong)));
    }

    @Transactional
    @Override
    public Ticket update(Ticket entity, Long aLong) {
        Ticket ticket = findById(aLong);
        TicketMapper.INSTANCE.updateTicket(ticket, entity);
        return ticketRepository.save(ticket);
    }

    @Transactional
    @Override
    public Ticket deleteById(Long aLong) {
        Ticket ticket = findById(aLong);
        ticketRepository.deleteById(aLong);
        return ticket;
    }

    @Transactional
    @Override
    public Ticket assignTicketToUserReturn(Ticket ticket, Long userReturnId) {
        UserReturn userReturn = userReturnRepository.findById(userReturnId).orElseThrow(
                () -> new UserReturnNotFoundException(String.format("UserReturn[ID=%d] was not found!",
                        userReturnId)));

        Ticket ticket1 = new TicketBuilderImpl()
                .setTicketType(ticket.getTicketType())
                .setFine(ticket.getFine())
                .setUserReturn(userReturn)
                .build();
        return ticketRepository.save(ticket1);
    }

    @Transactional
    @Override
    public Ticket revokeTicketForUserReturn(Long ticketId) {

        Ticket ticket = this.findById(ticketId);

        ticket.setUserReturn(null);
        ticket = this.save(ticket);

        this.deleteById(ticketId);

        return ticket;
    }

    @Transactional
    @Override
    public Ticket payTicket(Long userId, Long ticketId) {
        Ticket ticket = findById(ticketId);
        userBalanceService.withdrawFromUserBalance(userId, ticket.getFine());
        ticket.setIsPaid(true);
        return ticketRepository.save(ticket);
    }
}

