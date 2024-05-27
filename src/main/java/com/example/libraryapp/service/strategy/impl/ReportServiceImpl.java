package com.example.libraryapp.service.strategy.impl;

import com.example.libraryapp.model.*;
import com.example.libraryapp.service.strategy.*;
import com.example.libraryapp.service.BookService;
import com.example.libraryapp.service.UserBorrowService;
import com.example.libraryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    private final UserBorrowService userBorrowService;
    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public ReportServiceImpl(UserBorrowService userBorrowService, UserService userService, BookService bookService) {
        this.userBorrowService = userBorrowService;
        this.userService = userService;
        this.bookService = bookService;
    }

    public Report makeReport(Long userId, String reportType){
        Report report;

        if (reportType.equals(ReportType.USER_BALANCE_REPORT.toString().toLowerCase())){
            report = makeUserBalanceReport(userId);
        } else if (reportType.equals(ReportType.BORROWED_BOOKS_REPORT.toString().toLowerCase())){
            report = makeBorrowedBooksReport(userId);
        } else if (reportType.equals(ReportType.ALL_BOOKS_REPORT.toString().toLowerCase())){
            report = makeAllBooksReport();
        } else {
            throw new RuntimeException("Undefined ReportType was chosen!");
        }

        return report;
    }

    private UserBalanceReport makeUserBalanceReport(Long userId){
        User user = userService.findById(userId);
        Long booksBorrowed = (long) userBorrowService.findByUserId(userId).size();

        return new UserBalanceReport(
                user.getEmail(),
                user.getBalance(),
                booksBorrowed
        );
    }

    private BorrowedBooksReport makeBorrowedBooksReport(Long userId){
        User user = userService.findById(userId);
        List<UserBorrow> borrowedBooksByUser = userBorrowService.findByUserId(userId);

        Map<Book, Boolean> borrowedBooks = new HashMap();
        for(UserBorrow userBorrow : borrowedBooksByUser){
            borrowedBooks.put(
                    userBorrow.getBook(),
                    userBorrow.getIssueDate().before(userBorrow.getDeadlineDate())
            );
        }

        return new BorrowedBooksReport(
                user.getEmail(),
                borrowedBooks
        );
    }

    private AllBooksReport makeAllBooksReport(){
        List<Book> allBooks = bookService.findAll();
        Long bookCount = (long) allBooks.size();

        Double avgPrice = allBooks.stream()
                .mapToDouble(Book::getValueRate).average().orElse(0.0);
        Double maxPrice = allBooks.stream()
                .mapToDouble(Book::getValueRate).max().orElse(0.0);
        Double minPrice = allBooks.stream()
                .mapToDouble(Book::getValueRate).min().orElse(0.0);

        return new AllBooksReport(
                bookCount,
                avgPrice,
                maxPrice,
                minPrice
        );
    }
}
