package com.example.libraryapp.controller;

import com.example.libraryapp.dto.TicketDTO;
import com.example.libraryapp.dto.UserReturnDTO;
import com.example.libraryapp.mapper.TicketMapper;
import com.example.libraryapp.mapper.UserReturnMapper;
import com.example.libraryapp.model.Ticket;
import com.example.libraryapp.model.UserReturn;
import com.example.libraryapp.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ticket")
public class TicketController {

    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/")
    public ResponseEntity<List<TicketDTO>>findAll(){
        List<Ticket>tickets = ticketService.findAll();
        List<TicketDTO>ticketDTOS = TicketMapper.INSTANCE.ticketsToTicketsDTO(tickets);
        return ResponseEntity.ok(ticketDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO>findById(@PathVariable(name = "id")Long id){
        Ticket ticket = ticketService.findById(id);
        TicketDTO ticketDTOS = TicketMapper.INSTANCE.ticketToTicketDTO(ticket);
        return ResponseEntity.ok(ticketDTOS);
    }

    @PostMapping("/")
    public ResponseEntity<TicketDTO>save(@RequestBody Ticket ticket){
        Ticket savedTicket = ticketService.save(ticket);
        TicketDTO ticketDTO = TicketMapper.INSTANCE.ticketToTicketDTO(savedTicket);
        return ResponseEntity.ok(ticketDTO);
    }

    @PostMapping("/user-return/{returnId}")
    public ResponseEntity<UserReturnDTO> assignTicketToUserReturn(@RequestBody Ticket ticket,
                                                                  @PathVariable("returnId") Long userReturnId){
        UserReturn userReturn = ticketService.assignTicketToUserReturn(ticket, userReturnId);
        UserReturnDTO userReturnDTO = UserReturnMapper.INSTANCE.userReturnToUserReturnDTO(userReturn);
        return ResponseEntity.ok(userReturnDTO);
    }

    @PostMapping("/{id}/user-return/{returnId}/revoke")
    ResponseEntity<UserReturnDTO> revokeTicketForUserReturn(@PathVariable("id")Long ticketId,
                                                            @PathVariable("returnId")Long returnId){
        UserReturn userReturn = ticketService.revokeTicketForUserReturn(ticketId, returnId);
        UserReturnDTO userReturnDTO = UserReturnMapper.INSTANCE.userReturnToUserReturnDTO(userReturn);
        return ResponseEntity.ok(userReturnDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TicketDTO>update(@PathVariable("id")Long id, @RequestBody Ticket ticket){
        Ticket savedTicket = ticketService.update(ticket, id);
        TicketDTO ticketDTO = TicketMapper.INSTANCE.ticketToTicketDTO(savedTicket);
        return ResponseEntity.ok(ticketDTO);
    }

    @PostMapping("/{id}/user/{userId}/pay")
    ResponseEntity<TicketDTO>payTicket(@PathVariable("userId")Long userId,
                                       @PathVariable("id") Long ticketId){
        Ticket ticket = ticketService.payTicket(userId, ticketId);
        TicketDTO ticketDTO = TicketMapper.INSTANCE.ticketToTicketDTO(ticket);
        return ResponseEntity.ok(ticketDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TicketDTO>deleteById(@PathVariable("id")Long id){
        Ticket deletedTicket = ticketService.deleteById(id);
        TicketDTO ticketDTO = TicketMapper.INSTANCE.ticketToTicketDTO(deletedTicket);
        return ResponseEntity.ok(ticketDTO);
    }





}
