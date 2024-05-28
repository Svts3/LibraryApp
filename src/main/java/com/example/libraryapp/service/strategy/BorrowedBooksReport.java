package com.example.libraryapp.service.strategy;

import com.example.libraryapp.dto.BookDTO;
import com.example.libraryapp.model.Book;
import com.example.libraryapp.service.strategy.Report;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BorrowedBooksReport implements Report {
    private String userEmail;
    private List<List<Object>> borrowedBooks;
}
