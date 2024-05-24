package com.example.libraryapp.dto;

import com.example.libraryapp.model.TicketType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketDTO {

    private Long id;

    private Double fine;

    private TicketType ticketType;

    private Boolean isPaid;

    private UserReturnDTO userReturn;

}
