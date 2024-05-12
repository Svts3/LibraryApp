package com.example.libraryapp.repository;

import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.BookGenre;
import static org.junit.jupiter.api.Assertions.*;

import com.example.libraryapp.model.User;
import com.example.libraryapp.model.UserBorrow;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    private BookRepository bookRepository;

    private BookGenreRepository bookGenreRepository;

    private UserRepository userRepository;

    private UserBorrowRepository userBorrowRepository;

    User user;

    Book book;

    @Autowired
    public BookRepositoryTest(BookRepository bookRepository, BookGenreRepository bookGenreRepository,
                              UserRepository userRepository, UserBorrowRepository userBorrowRepository) {
        this.bookRepository = bookRepository;
        this.bookGenreRepository = bookGenreRepository;
        this.userRepository = userRepository;
        this.userBorrowRepository = userBorrowRepository;
    }

    String bookGenre = "A";
    @BeforeEach
    void setUp() {
        BookGenre testGenre = new BookGenre(1L, bookGenre);
        testGenre = bookGenreRepository.save(testGenre);
        book = new Book();
        book.setId(1L);
        book.setGenres(new HashSet<>());
        book.getGenres().add(testGenre);
        book = bookRepository.save(book);
        Book book2 = new Book();
        book2.setId(2L);
        book2.setGenres(new HashSet<>());
        book.getGenres().add(testGenre);
        book2 = bookRepository.save(book2);

        user = new User();
        user.setEmail("test");
        user.setUserBorrows(new HashSet<>());
        user = userRepository.save(user);

        UserBorrow userBorrow = new UserBorrow();
        userBorrow.setUser(user);
        userBorrow.setBook(book);
        userBorrow = userBorrowRepository.save(userBorrow);
        user.getUserBorrows().add(userBorrow);
        user = userRepository.save(user);

    }

    @Test
    void testFindByGenre_WithValidGenre_ReturnValidBooks(){
        List<Book> books =  bookRepository.findByGenre(bookGenre);
        assertNotNull(books);
        assertEquals(2, books.size());
    }
    @Test
    void testFindByGenre_WithInvalidGenre_ReturnEmptyCollection(){
        List<Book> books = bookRepository.findByGenre("");
        assertNotNull(books);
        assertEquals(0, books.size());
    }

    @Test
    void testFindByUserId_WithValidUserId_ReturnValidBooks(){

        List<Book>books = bookRepository.findByUserId(user.getId());

        assertNotNull(books);
        assertEquals(1, books.size());
    }
    @Test
    void testFindByUserId_WithInvalidUserId_ReturnValidBooks(){

        List<Book>books = bookRepository.findByUserId(-5L);

        assertNotNull(books);
        assertEquals(0, books.size());
    }


}
