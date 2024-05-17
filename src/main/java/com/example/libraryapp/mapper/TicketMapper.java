package com.example.libraryapp.mapper;

import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.Ticket;
import org.mapstruct.*;

@Mapper
public interface TicketMapper {

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTicket(@MappingTarget Ticket ticket, Ticket updatedTicket);
}
