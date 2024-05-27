package com.example.libraryapp.service.strategy;

import com.example.libraryapp.model.Book;
import com.example.libraryapp.service.strategy.Report;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BorrowedBooksReport implements Report {
    private String userEmail;
    private Map<Book, Boolean> borrowedBooks = new HashMap<Book, Boolean>();
}
