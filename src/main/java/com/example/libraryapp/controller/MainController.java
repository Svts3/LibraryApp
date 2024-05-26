package com.example.libraryapp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class MainController {

    @GetMapping("/")
    public String home(){
        return "success";
    }

}
