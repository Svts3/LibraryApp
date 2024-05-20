package com.example.libraryapp.service.impl;

import com.example.libraryapp.builder.TicketBuilder;
import com.example.libraryapp.builder.impl.TicketBuilderImpl;
import com.example.libraryapp.exception.TicketNotFoundException;
import com.example.libraryapp.exception.TicketTypeNotFoundException;
import com.example.libraryapp.exception.UserReturnNotFoundException;
import com.example.libraryapp.mapper.TicketMapper;
import com.example.libraryapp.model.Ticket;
import com.example.libraryapp.model.TicketType;
import com.example.libraryapp.model.UserReturn;
import com.example.libraryapp.repository.TicketRepository;
import com.example.libraryapp.repository.TicketTypeRepository;
import com.example.libraryapp.repository.UserReturnRepository;
import com.example.libraryapp.service.TicketService;
import com.example.libraryapp.service.UserBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;

    private TicketTypeRepository ticketTypeRepository;

    private UserReturnRepository userReturnRepository;

    private UserBalanceService userBalanceService;

    private TicketMapper ticketMapper;
    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, TicketTypeRepository ticketTypeRepository,
                             UserReturnRepository userReturnRepository, UserBalanceService userBalanceService) {
        this.ticketRepository = ticketRepository;
        this.ticketTypeRepository = ticketTypeRepository;
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
                ()->new TicketNotFoundException(String.format("Ticket[ID=%d] was not found!", aLong)));
    }

    @Override
    public Ticket update(Ticket entity, Long aLong) {
        Ticket ticket = findById(aLong);
        ticketMapper.updateTicket(ticket, entity);
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket deleteById(Long aLong) {
        Ticket ticket = findById(aLong);
        ticketRepository.deleteById(aLong);
        return ticket;
    }

    @Override
    public UserReturn assignTicketToUserReturn(String ticketType, Double fine, Long userId, Long userReturnId) {
        TicketType ticketType1 = ticketTypeRepository.findByTicketType(ticketType).orElseThrow(
                ()->new TicketTypeNotFoundException(String.format("TicketType[type=%s] was not found!",
                        ticketType)));
        UserReturn userReturn = userReturnRepository.findById(userReturnId).orElseThrow(
                ()->new UserReturnNotFoundException(String.format("UserReturn[ID=%d] was not found!",
                        userReturnId)));

        Ticket ticket = new TicketBuilderImpl()
        .setTicketType(ticketType1)
        .setFine(fine)
        .setUserReturn(userReturn)
                .build();

        ticketRepository.save(ticket);
        return userReturn;
    }

    @Override
    public UserReturn revokeTicketForUserReturn(Long ticketId, Long userReturnId) {
        UserReturn userReturn = userReturnRepository.findById(userReturnId).orElseThrow(
                ()->new UserReturnNotFoundException(String.format("UserReturn[ID=%d] was not found!", userReturnId)));

        userReturn.getTickets().removeIf(t->t.getId().equals(ticketId));

        return userReturnRepository.save(userReturn);
    }

    @Override
    public Ticket payTicket(Long userId, Long ticketId) {
        Ticket ticket = findById(ticketId);
        userBalanceService.withdrawFromUserBalance(userId, ticket.getFine());
        ticket.setIsPaid(true);
        return ticketRepository.save(ticket);
    }
}

