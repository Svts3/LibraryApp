package com.example.libraryapp.service.strategy;

import com.example.libraryapp.service.strategy.Report;
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
