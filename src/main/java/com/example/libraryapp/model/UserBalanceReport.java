package com.example.libraryapp.model;

import com.example.libraryapp.model.interfaces.Report;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserBalanceReport implements Report {
    private String userEmail;
    private Double balance;
    private Long booksBorrowed;
}
