package com.example.libraryapp.controller;

import com.example.libraryapp.dto.UserReturnDTO;
import com.example.libraryapp.dto.UserReturnRequest;
import com.example.libraryapp.mapper.UserReturnMapper;
import com.example.libraryapp.model.UserReturn;
import com.example.libraryapp.service.UserReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-return")
public class UserReturnController {

    private UserReturnService userReturnService;

    @Autowired
    public UserReturnController(UserReturnService userReturnService) {
        this.userReturnService = userReturnService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<UserReturnDTO>> findByUserId(@PathVariable("id") Long userId) {
        List<UserReturn> userReturns = userReturnService.findByUserId(userId);
        List<UserReturnDTO> userReturnDTOS = UserReturnMapper.INSTANCE.userReturnsToUserReturnDTOS(userReturns);
        return ResponseEntity.ok(userReturnDTOS);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<List<UserReturnDTO>> findByBookId(@PathVariable("id") Long bookId) {
        List<UserReturn> userReturns = userReturnService.findByBookId(bookId);
        List<UserReturnDTO> userReturnDTOS = UserReturnMapper.INSTANCE.userReturnsToUserReturnDTOS(userReturns);
        return ResponseEntity.ok(userReturnDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserReturnDTO> findById(@PathVariable("id") Long id) {
        UserReturn userReturn = userReturnService.findById(id);
        UserReturnDTO userReturnDTO = UserReturnMapper.INSTANCE.userReturnToUserReturnDTO(userReturn);
        return ResponseEntity.ok(userReturnDTO);
    }
    @PostMapping("/")
    public ResponseEntity<UserReturnDTO> returnBook(@RequestBody UserReturnRequest userReturnRequest) {
        UserReturn userReturn = userReturnService.returnBook(userReturnRequest);
        UserReturnDTO userReturnDTO = UserReturnMapper.INSTANCE.userReturnToUserReturnDTO(userReturn);
        return ResponseEntity.ok(userReturnDTO);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<UserReturnDTO> deleteById(@PathVariable("id")Long id) {
        UserReturn userReturn = userReturnService.deleteById(id);
        UserReturnDTO userReturnDTO = UserReturnMapper.INSTANCE.userReturnToUserReturnDTO(userReturn);
        return ResponseEntity.ok(userReturnDTO);
    }
}
