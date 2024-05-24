package com.example.libraryapp.controller;

import com.example.libraryapp.dto.UserDTO;
import com.example.libraryapp.mapper.UserMapper;
import com.example.libraryapp.model.User;
import com.example.libraryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO>findById(@PathVariable("id")Long id){
        User user = userService.findById(id);
        UserDTO userDTO = UserMapper.INSTANCE.userToUserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<UserDTO>findByBookId(@PathVariable("id")Long id){
        User user = userService.findByBookId(id);
        UserDTO userDTO = UserMapper.INSTANCE.userToUserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/")
    public ResponseEntity<UserDTO>save(@RequestBody User user){
        User savedUser = userService.save(user);
        UserDTO userDTO = UserMapper.INSTANCE.userToUserDTO(savedUser);
        return ResponseEntity.ok(userDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO>update(@PathVariable("id")Long id, @RequestBody User user){
        User updatedUser = userService.update(user, id);
        UserDTO userDTO = UserMapper.INSTANCE.userToUserDTO(updatedUser);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO>deleteById(@PathVariable("id")Long id){
        User user = userService.deleteById(id);
        UserDTO userDTO = UserMapper.INSTANCE.userToUserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

}
