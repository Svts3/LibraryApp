package com.example.libraryapp.model;

import com.example.libraryapp.model.interfaces.Report;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
