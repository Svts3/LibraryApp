package com.example.libraryapp.mapper;

import com.example.libraryapp.dto.TicketDTO;
import com.example.libraryapp.model.Ticket;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TicketMapper {

    TicketMapper INSTANCE = Mappers.getMapper( TicketMapper.class );
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTicket(@MappingTarget Ticket ticket, Ticket updatedTicket);

    TicketDTO ticketToTicketDTO(Ticket ticket);

    List<TicketDTO>ticketsToTicketsDTO(List<Ticket>tickets);
}
