package com.example.libraryapp.service.impl;

import com.example.libraryapp.builder.impl.UserBorrowBuilderImpl;
import com.example.libraryapp.exception.BookNotAvailableException;
import com.example.libraryapp.exception.UserBorrowNotFoundException;
import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.BookStatus;
import com.example.libraryapp.model.User;
import com.example.libraryapp.model.UserBorrow;
import com.example.libraryapp.repository.UserBorrowRepository;
import com.example.libraryapp.service.BookService;
import com.example.libraryapp.service.UserBalanceService;
import com.example.libraryapp.service.UserBorrowService;
import com.example.libraryapp.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserBorrowServiceImpl implements UserBorrowService {

    private UserBorrowRepository userBorrowRepository;

    private UserBalanceService userBalanceService;

    private UserService userService;

    private BookService bookService;


    @Autowired
    public UserBorrowServiceImpl(UserBorrowRepository userBorrowRepository, UserBalanceService userBalanceService, BookService bookService, UserService userService) {
        this.userBorrowRepository = userBorrowRepository;
        this.userBalanceService = userBalanceService;
        this.bookService = bookService;
        this.userService = userService;
    }

    @Override
    public List<UserBorrow> findAll() {
        return userBorrowRepository.findAll();
    }

    @Override
    public UserBorrow findById(Long aLong) {
        return userBorrowRepository.findById(aLong).orElseThrow(() -> new UserBorrowNotFoundException(String.format("UserBorrow[ID=%d] was not found!", aLong)));
    }

    @Transactional
    @Override
    public UserBorrow borrowBook(UserBorrow userBorrow) {
        Book book = bookService.findById(userBorrow.getBook().getId());
        User user = userService.findById(userBorrow.getUser().getId());

        this.checkBookStatus(book);
        UserBorrow userBorrow1 = new UserBorrowBuilderImpl()
                .setUser(user)
                .setBook(book)
                .setIssueDate(userBorrow
                        .getIssueDate())
                .setDeadlineDate(userBorrow.getDeadlineDate())
                .build();
        userBorrow1 = this.save(userBorrow1);

        Double securityDeposit = calculateDiscount(book, user);
        userBalanceService.withdrawFromUserBalance(user.getId(), securityDeposit);

        book.setBookStatus(BookStatus.IS_BORROWED);
        bookService.save(book);

        return userBorrow1;
    }

    private Double calculateDiscount(Book book, User user) {
        DiscountStrategyExecutor discountStrategyExecutor;

        String userCategoryName = user.getUserCategory().getCategoryName();
        if (userCategoryName.equalsIgnoreCase("STUDENT")) {
            discountStrategyExecutor = new DiscountStrategyExecutor(new StudentDiscountStrategy());
        } else if (userCategoryName.equalsIgnoreCase("SENIOR")) {
            discountStrategyExecutor = new DiscountStrategyExecutor(new SeniorDiscountStrategy());
        } else if (userCategoryName.equalsIgnoreCase("FREQUENT_BORROWER")) {
            discountStrategyExecutor = new DiscountStrategyExecutor(new FrequentBorrowerDiscountStrategy());
        } else {
            discountStrategyExecutor = new DiscountStrategyExecutor(new CustomerDiscountStrategy());
        }
        return discountStrategyExecutor.executeStrategy(book);
    }

    private void checkBookStatus(Book book) {
        if (book.getBookStatus().equals(BookStatus.IS_NOT_AVAILABLE)) {
            throw new BookNotAvailableException(String.format("Book[ID=%d] is not available", book.getId()));
        } else if (book.getBookStatus().equals(BookStatus.IS_BORROWED)) {
            throw new BookNotAvailableException(String.format("Book[ID=%d] is borrowed", book.getId()));
        }
    }

    @Override
    public UserBorrow save(UserBorrow entity) {
        return userBorrowRepository.save(entity);
    }

    @Transactional
    @Override
    public UserBorrow deleteById(Long aLong) {
        UserBorrow userBorrow = this.findById(aLong);
        userBorrowRepository.deleteById(aLong);
        return userBorrow;
    }

    @Override
    public List<UserBorrow> findByUserId(Long userId) {
        return userBorrowRepository.findByUserId(userId);
    }

    @Override
    public List<UserBorrow> findByBookId(Long bookId) {
        return userBorrowRepository.findByBookId(bookId);
    }

    @Override
    public List<UserBorrow> findOverdueBorrowsByUserId(Long userId) {
        return userBorrowRepository.findOverdueBorrowsByUserId(userId);
    }

    @Override
    public List<UserBorrow> findEarlyBorrowsByUserId(Long userId) {
        return userBorrowRepository.findEarlyBorrowsByUserId(userId);
    }

    @Override
    public List<UserBorrow> findByIssueIdInRange(Date min, Date max) {
        return userBorrowRepository.findByIssueIdInRange(min, max);
    }
}
