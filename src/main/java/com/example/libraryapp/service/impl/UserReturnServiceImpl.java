package com.example.libraryapp.service.impl;

import com.example.libraryapp.builder.impl.UserReturnBuilderImpl;
import com.example.libraryapp.dto.UserReturnRequest;
import com.example.libraryapp.exception.BookReturnException;
import com.example.libraryapp.exception.UserReturnNotFoundException;
import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.BookStatus;
import com.example.libraryapp.model.UserBorrow;
import com.example.libraryapp.model.UserReturn;
import com.example.libraryapp.repository.UserReturnRepository;
import com.example.libraryapp.service.BookService;
import com.example.libraryapp.service.UserBalanceService;
import com.example.libraryapp.service.UserBorrowService;
import com.example.libraryapp.service.UserReturnService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UserReturnServiceImpl implements UserReturnService {


    private UserReturnRepository userReturnRepository;

    private UserBorrowService userBorrowService;

    private UserBalanceService userBalanceService;

    private BookService bookService;

    @Autowired
    public UserReturnServiceImpl(UserReturnRepository userReturnRepository, UserBorrowService userBorrowService,
                                 UserBalanceService userBalanceService, BookService bookService) {
        this.userReturnRepository = userReturnRepository;
        this.userBorrowService = userBorrowService;
        this.userBalanceService = userBalanceService;
        this.bookService = bookService;
    }

    @Override
    public List<UserReturn> findAll() {
        return userReturnRepository.findAll();
    }

    @Override
    public UserReturn findById(Long aLong) {
        return userReturnRepository.findById(aLong).orElseThrow(
                () -> new UserReturnNotFoundException(String.format("UserReturn[ID=%d] was not found!",
                        aLong)));
    }

    @Override
    public UserReturn save(UserReturn entity) {
        return userReturnRepository.save(entity);
    }

    @Transactional
    @Override
    public UserReturn deleteById(Long aLong) {
        UserReturn userReturn = this.findById(aLong);
        userReturnRepository.deleteById(aLong);
        return userReturn;
    }

    @Transactional
    @Override
    public UserReturn returnBook(UserReturnRequest userReturnRequest) {
        Optional<UserBorrow> userBorrow = userBorrowService.findByUserId(userReturnRequest.getUser().getId())
                .stream()
                .filter((borrow) -> borrow.getBook().getId().equals(userReturnRequest.getBook().getId()))
                .filter((borrow)->borrow.getUser().getId().equals(userReturnRequest.getUser().getId()))
                .max(Comparator.comparing(UserBorrow::getIssueDate));

        if (userBorrow.isEmpty()) {
            throw new BookReturnException(String.format("Book[ID=%d] was not borrowed by User[ID=%d]",
                    userReturnRequest.getBook().getId(), userReturnRequest.getUser().getId()));
        }

        UserReturn userReturn = new UserReturnBuilderImpl()
                .setBook(userBorrow.get().getBook())
                .setUser(userBorrow.get().getUser())
                .build();
        userReturn = this.save(userReturn);

        Book book = userReturn.getBook();
        book.setBookStatus(BookStatus.IS_AVAILABLE);
        userBalanceService.depositToUserBalance(userReturnRequest.getUser().getId(), book.getSecurityDeposit());

        bookService.save(book);
        return userReturn;
    }

    @Override
    public List<UserReturn> findByUserId(Long userId) {
        return userReturnRepository.findByUserId(userId);
    }

    @Override
    public List<UserReturn> findByBookId(Long bookId) {
        return userReturnRepository.findByBookId(bookId);
    }
}
