package com.example.libraryapp.controller;

import com.example.libraryapp.dto.UserBorrowDTO;
import com.example.libraryapp.mapper.UserBorrowMapper;
import com.example.libraryapp.model.UserBorrow;
import com.example.libraryapp.service.UserBorrowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user-borrow")
public class UserBorrowController {

    private UserBorrowService userBorrowService;

    public UserBorrowController(UserBorrowService userBorrowService) {
        this.userBorrowService = userBorrowService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserBorrowDTO>> findAll() {
        List<UserBorrow> userBorrows = userBorrowService.findAll();
        List<UserBorrowDTO> userBorrowDTOS = UserBorrowMapper.INSTANCE.userBorrowsToUserBorrowDTOS(userBorrows);
        return ResponseEntity.ok(userBorrowDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserBorrowDTO> findById(@PathVariable("id") Long id) {
        UserBorrow userBorrow = userBorrowService.findById(id);
        UserBorrowDTO userBorrowDTO = UserBorrowMapper.INSTANCE.userBorrowToUserBorrowDTO(userBorrow);
        return ResponseEntity.ok(userBorrowDTO);
    }

    @GetMapping("/user/{id}")
    ResponseEntity<List<UserBorrowDTO>> findByUserId(@PathVariable("id")Long userId){
        List<UserBorrow> userBorrows = userBorrowService.findByUserId(userId);
        List<UserBorrowDTO> userBorrowDTOS = UserBorrowMapper.INSTANCE.userBorrowsToUserBorrowDTOS(userBorrows);
        return ResponseEntity.ok(userBorrowDTOS);
    }
    @GetMapping("/book/{id}")
    ResponseEntity<List<UserBorrowDTO>>findByBookId(@PathVariable("id")Long bookId){
        List<UserBorrow> userBorrows = userBorrowService.findByBookId(bookId);
        List<UserBorrowDTO> userBorrowDTOS = UserBorrowMapper.INSTANCE.userBorrowsToUserBorrowDTOS(userBorrows);
        return ResponseEntity.ok(userBorrowDTOS);
    }


    @PostMapping("/")
    ResponseEntity<UserBorrowDTO> borrowBook(@RequestBody UserBorrow userBorrow){
        UserBorrow userBorrow1 = userBorrowService.borrowBook(userBorrow);
        UserBorrowDTO userBorrowDTO = UserBorrowMapper.INSTANCE.userBorrowToUserBorrowDTO(userBorrow1);
        return ResponseEntity.ok(userBorrowDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<UserBorrowDTO>deleteById(@PathVariable("id")Long id){
        UserBorrow savedUserBorrow = userBorrowService.deleteById(id);
        UserBorrowDTO userBorrowDTO = UserBorrowMapper.INSTANCE.userBorrowToUserBorrowDTO(savedUserBorrow);
        return ResponseEntity.ok(userBorrowDTO);
    }


}
