package com.example.libraryapp.service.impl;

import com.example.libraryapp.builder.UserReturnBuilder;
import com.example.libraryapp.builder.impl.UserBorrowBuilderImpl;
import com.example.libraryapp.builder.impl.UserReturnBuilderImpl;
import com.example.libraryapp.exception.BookNotAvailableException;
import com.example.libraryapp.exception.BookNotFoundException;
import com.example.libraryapp.exception.BookReturnException;
import com.example.libraryapp.mapper.BookMapper;
import com.example.libraryapp.model.*;
import com.example.libraryapp.repository.BookRepository;
import com.example.libraryapp.service.BookService;
import com.example.libraryapp.service.UserBorrowService;
import com.example.libraryapp.service.UserReturnService;
import com.example.libraryapp.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    private UserBorrowService userBorrowService;

    private UserService userService;


    private UserReturnService userReturnService;

    private UserBalanceServiceImpl userBalanceService;


    private BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, UserBorrowService userBorrowService,
                           UserService userService,
                           UserReturnService userReturnService, UserBalanceServiceImpl userBalanceService) {
        this.bookRepository = bookRepository;
        this.userBorrowService = userBorrowService;
        this.userService = userService;
        this.userReturnService = userReturnService;
        this.userBalanceService = userBalanceService;
    }

    @Override
    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title).orElseThrow(
                () -> new BookNotFoundException(String.format("Book[title=%s] was not found!", title)));
    }

    @Override
    public List<Book> findByBookStatus(String bookStatus) {
        return bookRepository.findByBookStatus(bookStatus);
    }

    @Override
    public List<Book> findByCoverType(String coverType) {
        return bookRepository.findByCoverType(coverType);
    }

    @Override
    public List<Book> findByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    @Override
    public List<Book> findByDiscountValueAndMeasure(Double value, String measure) {
        return bookRepository.findByDiscountValueAndMeasure(value, measure);
    }

    @Override
    public List<Book> findByUserId(Long userId) {
        return bookRepository.findByUserId(userId);
    }

    @Transactional
    @Override
    public Book borrowBook(Long userId, Long bookId, Date issueDate, Date deadlineDate) {
        Book book = findById(bookId);
        User user = userService.findById(userId);

        this.checkBookStatus(book);

        UserBorrow userBorrow = new UserBorrowBuilderImpl()
                .setBook(book)
                .setUser(user)
                .setDeadlineDate(deadlineDate)
                .setIssueDate(issueDate)
                .build();

        userBorrowService.save(userBorrow);

        userBalanceService.withdrawFromUserBalance(user.getId(), book.getSecurityDeposit());

        book.setBookStatus(BookStatus.IS_BORROWED);
        bookRepository.save(book);

        return userBorrow.getBook();
    }

    private void checkBookStatus(Book book) {
        if (book.getBookStatus().equals(BookStatus.IS_NOT_AVAILABLE)) {
            throw new BookNotAvailableException(String.format("Book[ID=%d] is not available", book.getId()));
        } else if (book.getBookStatus().equals(BookStatus.IS_BORROWED)) {
            throw new BookNotAvailableException(String.format("Book[ID=%d] is borrowed", book.getId()));
        }
    }

    @Transactional
    @Override
    public Book returnBook(Long userId, Long bookId) {
        Optional<UserBorrow> userBorrow = userBorrowService.findByUserId(userId)
                .stream()
                .filter((borrow) -> borrow.getBook().getId().equals(bookId))
                .max(Comparator.comparing(UserBorrow::getIssueDate));

        if (userBorrow.isEmpty()) {
            throw new BookReturnException(String.format("Book[ID=%d] was not borrowed by User[ID=%d]",
                    bookId, userId));
        }

        UserReturn userReturn = new UserReturnBuilderImpl()
        .setBook(userBorrow.get().getBook())
        .setUser(userBorrow.get().getUser())
                .build();
        userReturn = userReturnService.save(userReturn);

        Book book = userReturn.getBook();
        book.setBookStatus(BookStatus.IS_AVAILABLE);
        userBalanceService.depositToUserBalance(userId, book.getSecurityDeposit());

        bookRepository.save(book);
        return userReturn.getBook();
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book save(Book entity) {
        return bookRepository.save(entity);
    }

    @Override
    public Book findById(Long aLong) {
        return bookRepository.findById(aLong).orElseThrow(
                () -> new BookNotFoundException(String.format("Book [ID=%d] was not found!", aLong)));
    }

    @Transactional
    @Override
    public Book update(Book entity, Long aLong) {
        Book book = findById(aLong);
        bookMapper.updateBook(book, entity);
        return book;
    }

    @Transactional
    @Override
    public Book deleteById(Long aLong) {
        Book book = findById(aLong);
        bookRepository.deleteById(aLong);
        return book;
    }
}
