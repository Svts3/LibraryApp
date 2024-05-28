package com.example.libraryapp.service.strategy.impl;

import com.example.libraryapp.dto.BookDTO;
import com.example.libraryapp.mapper.BookMapper;
import com.example.libraryapp.mapper.BookMapperImpl;
import com.example.libraryapp.model.*;
import com.example.libraryapp.service.strategy.*;
import com.example.libraryapp.service.BookService;
import com.example.libraryapp.service.UserBorrowService;
import com.example.libraryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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

        List<List<Object>> borrowedBooks = new ArrayList<>();

        List<String> booksNames = new ArrayList<>();

        for(UserBorrow userBorrow : borrowedBooksByUser){
            if(userBorrow.getBook().getBookStatus().equals(BookStatus.IS_AVAILABLE) && !booksNames.contains(userBorrow.getBook().getTitle())) {
                booksNames.add(userBorrow.getBook().getTitle());

                List<Date> dates = new ArrayList<>();
                List<Object> map = new ArrayList<>();

                dates.add(userBorrow.getIssueDate());
                dates.add(userBorrow.getDeadlineDate());

//                map.put(
//                        BookMapper.INSTANCE.bookToBookDTO(userBorrow.getBook()),
//                        dates
//                );

                map.add(BookMapper.INSTANCE.bookToBookDTO(userBorrow.getBook()));
                map.add(dates);

                borrowedBooks.add(
                        map
                );
            }
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
