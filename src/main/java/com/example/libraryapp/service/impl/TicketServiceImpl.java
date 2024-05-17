package com.example.libraryapp.service.impl;

import com.example.libraryapp.builder.TicketBuilder;
import com.example.libraryapp.builder.UserReturnBuilder;
import com.example.libraryapp.builder.impl.TicketBuilderImpl;
import com.example.libraryapp.builder.impl.UserReturnBuilderImpl;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;

    private TicketTypeRepository ticketTypeRepository;

    private UserReturnRepository userReturnRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, TicketTypeRepository ticketTypeRepository,
                             UserReturnRepository userReturnRepository) {
        this.ticketRepository = ticketRepository;
        this.ticketTypeRepository = ticketTypeRepository;
        this.userReturnRepository = userReturnRepository;
    }
    private TicketMapper ticketMapper;

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
    public Ticket assignTicketToUserReturn(String ticketType, Double fine, Long userId, Long userReturnId) {
        TicketType ticketType1 = ticketTypeRepository.findByTicketType(ticketType).orElseThrow(
                ()->new TicketTypeNotFoundException(String.format("TicketType[type=%s] was not found!", ticketType)));

        TicketBuilder ticketBuilder = new TicketBuilderImpl(new Ticket());
        ticketBuilder.setTicketType(ticketType1);
        ticketBuilder.setFine(fine);

        UserReturn userReturn = userReturnRepository.findById(userReturnId).orElseThrow(
                ()->new UserReturnNotFoundException(String.format("UserReturn[ID=%d] was not found!", userReturnId)));
        ticketBuilder.setUserReturn(userReturn);
        return ticketRepository.save(ticketBuilder.build());
    }

    @Override
    public Ticket cancelTicketForUserReturn(Long ticketId, Long userReturnId) {
        UserReturn userReturn = userReturnRepository.findById(userReturnId).orElseThrow(
                ()->new UserReturnNotFoundException(String.format("UserReturn[ID=%d] was not found!", userReturnId)));
        Ticket ticket = userReturn.getTickets().stream().filter(t->t.getId().equals(ticketId)).toList().getFirst();
        return deleteById(ticket.getId());
    }

    @Override
    public Ticket payTicket(Long userId, Long ticketId) {
        Ticket ticket = findById(ticketId);
        ticket.setIsPaid(true);
        return ticketRepository.save(ticket);
    }
}

