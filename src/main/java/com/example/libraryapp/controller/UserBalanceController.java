package com.example.libraryapp.controller;

import com.example.libraryapp.dto.UserDTO;
import com.example.libraryapp.mapper.UserMapper;
import com.example.libraryapp.model.User;
import com.example.libraryapp.service.UserBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user-balance")
public class UserBalanceController {

    private UserBalanceService userBalanceService;

    @Autowired
    public UserBalanceController(UserBalanceService userBalanceService) {
        this.userBalanceService = userBalanceService;
    }

    @PostMapping("/user/{id}/deposit/{amount}")
    public ResponseEntity<UserDTO> depositToUserBalance(@PathVariable("id")Long userId,
                                                        @PathVariable("amount") Double amount) {
        User user = userBalanceService.depositToUserBalance(userId, amount);
        UserDTO userDTO = UserMapper.INSTANCE.userToUserDTO(user);
        return ResponseEntity.ok(userDTO);
    }
    @PostMapping("/user/{id}/withdraw/{amount}")
    public ResponseEntity<UserDTO> withdrawFromUserBalance(@PathVariable("id")Long userId,
                                                           @PathVariable("amount")Double amount) {
        User user = userBalanceService.withdrawFromUserBalance(userId, amount);
        UserDTO userDTO = UserMapper.INSTANCE.userToUserDTO(user);
        return ResponseEntity.ok(userDTO);
    }
}
