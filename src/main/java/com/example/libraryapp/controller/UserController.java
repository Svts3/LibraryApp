package com.example.libraryapp.controller;

import com.example.libraryapp.dto.UserDTO;
import com.example.libraryapp.mapper.UserMapper;
import com.example.libraryapp.model.ReportType;
import com.example.libraryapp.model.User;
import com.example.libraryapp.service.strategy.Report;
import com.example.libraryapp.service.strategy.ReportService;
import com.example.libraryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private ReportService reportService;

    @Autowired
    public UserController(UserService userService, ReportService reportService) {
        this.userService = userService;
        this.reportService = reportService;
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

    @GetMapping("/{id}/report")
    public ResponseEntity<Report> balanceById(@PathVariable("id")Long id, @RequestParam("type") String reportType){
        User user = userService.findById(id);

        String strategy = "sfewef3weffdsf";
        if(reportType.equals(ReportType.BORROWED_BOOKS_REPORT.toString().toLowerCase())){
            strategy = "borrowed_books_report";
        } else if (reportType.equals(ReportType.USER_BALANCE_REPORT.toString().toLowerCase())){
            strategy = "user_balance_report";
        } else if (reportType.equals(ReportType.ALL_BOOKS_REPORT.toString().toLowerCase())){
            strategy = "all_books_report";
        }

        System.out.println(strategy);

        Report report = reportService.makeReport(user.getId(), strategy);

        return ResponseEntity.ok(report);
    }

}
