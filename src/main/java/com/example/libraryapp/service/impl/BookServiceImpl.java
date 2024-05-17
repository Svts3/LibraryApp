package com.example.libraryapp.service.impl;

import com.example.libraryapp.builder.UserBorrowBuilder;
import com.example.libraryapp.builder.UserReturnBuilder;
import com.example.libraryapp.builder.impl.UserBorrowBuilderImpl;
import com.example.libraryapp.builder.impl.UserReturnBuilderImpl;
import com.example.libraryapp.exception.*;
import com.example.libraryapp.mapper.BookMapper;
import com.example.libraryapp.model.*;
import com.example.libraryapp.repository.*;
import com.example.libraryapp.service.BookService;
import com.example.libraryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    private UserBorrowRepository userBorrowRepository;

    private DiscountRepository discountRepository;

    private DiscountMeasureRepository discountMeasureRepository;

    private UserService userService;

    private BookStatusRepository bookStatusRepository;

    private UserReturnRepository userReturnRepository;

    private BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, UserBorrowRepository userBorrowRepository,
                           DiscountRepository discountRepository,
                           DiscountMeasureRepository discountMeasureRepository,
                           UserService userService, BookStatusRepository bookStatusRepository,
                           UserReturnRepository userReturnRepository) {
        this.bookRepository = bookRepository;
        this.userBorrowRepository = userBorrowRepository;
        this.discountRepository = discountRepository;
        this.discountMeasureRepository = discountMeasureRepository;
        this.userService = userService;
        this.bookStatusRepository = bookStatusRepository;
        this.userReturnRepository = userReturnRepository;
    }

    @Override
    public Book assignDiscountToBook(Long discountMeasureId, Double discountValue, Long bookId) {
        DiscountMeasure discountMeasure  = discountMeasureRepository.findById(discountMeasureId)
                .orElseThrow(
                        ()->new DiscountMeasureNotFoundException(
                                String.format("DiscountMeasure[ID=%d]was not found!", discountMeasureId)));

        Optional<Discount> discountOptional = discountRepository
                .findByValueAndDiscountMeasure(discountValue, discountMeasure.getMeasureName());

        if(discountOptional.isEmpty()){
            discountOptional = Optional.of(discountRepository.save(new Discount(discountValue, discountMeasure)));
        }

        Book book = findById(bookId);
        book.setDiscount(discountOptional.get());

        return save(book);
    }

    @Override
    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title).orElseThrow(
                ()->new BookNotFoundException(String.format("Book[title=%s] was not found!",title)));
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

    @Override
    public Book borrowBook(Long userId, Long bookId, Date issueDate, Date deadlineDate) {
        UserBorrowBuilder userBorrowBuilder = new UserBorrowBuilderImpl(new UserBorrow());
        Book book = findById(bookId);

        if(book.getBookStatus().getStatusName().equalsIgnoreCase("Not Available")){
            throw new BookNotAvailableException(String.format("Book[ID=%d] is not available", bookId));
        }

        userBorrowBuilder.setBook(book);
        User user = userService.findById(userId);

        userBorrowBuilder.setUser(user);
        userBorrowBuilder.setDeadlineDate(deadlineDate);
        userBorrowBuilder.setIssueDate(issueDate);
        return save(userBorrowBuilder.build().getBook());
    }

    @Override
    public Book returnBook(Long userId, Long bookId) {
        Optional<UserBorrow> userBorrow = userBorrowRepository.findByUserId(userId)
                .stream()
                .filter((borrow)->borrow.getBook().getId().equals(bookId))
                .max(Comparator.comparing(UserBorrow::getIssueDate));

        if(userBorrow.isEmpty()){
            throw new BookReturnException(String.format("Book[ID=%d] was not borrowed by User[ID=%d]",
                    bookId, userId));
        }
        UserReturnBuilder userReturnBuilder = new UserReturnBuilderImpl(new UserReturn());

        userReturnBuilder.setBook(userBorrow.get().getBook());
        userReturnBuilder.setUser(userBorrow.get().getUser());
        UserReturn userReturn = userReturnBuilder.build();
        userReturn = userReturnRepository.save(userReturn);
        Book book = userReturn.getBook();
        book.setBookStatus(bookStatusRepository.findByStatusName("Available")
                .orElseThrow(()->new BookStatusNotFoundException(
                        String.format("BookStatus[ID=%d] was not found!", book.getBookStatus().getId()))));
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
                ()->new BookNotFoundException(String.format("Book [ID=%d] was not found!", aLong)));
    }

    @Override
    public Book update(Book entity, Long aLong) {
        Book book = findById(aLong);
        bookMapper.updateBook(book, entity);
        return book;
    }

    @Override
    public Book deleteById(Long aLong) {
        Book book = findById(aLong);
        bookRepository.deleteById(aLong);
        return book;
    }
}
