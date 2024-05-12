package com.example.libraryapp.repository;

import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.User;
import com.example.libraryapp.model.UserBorrow;
import com.example.libraryapp.model.UserCategory;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

    private UserRepository userRepository;
    private BookRepository bookRepository;
    private UserBorrowRepository userBorrowRepository;

    private UserCategoryRepository userCategoryRepository;

    private User user;

    private Book book;

    private Book book1;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository, BookRepository bookRepository,
                              UserBorrowRepository userBorrowRepository,
                              UserCategoryRepository userCategoryRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.userBorrowRepository = userBorrowRepository;
        this.userCategoryRepository = userCategoryRepository;
    }

    @BeforeEach
    void setUp(){
    userCategoryRepository.save(new UserCategory(1L, "A"));
        Book entity = new Book();
        entity.setTitle("test");
        entity.setId(1L);
        entity = bookRepository.save(entity);
        book1 = new Book();
        book1.setId(2L);
        book1.setTitle("test2");
        book1 = bookRepository.save(book1);
        user = new User();
        user.setEmail("test.test@gmail.com");
        userRepository.save(user);
        UserBorrow userBorrow = new UserBorrow();
        userBorrow.setUser(user);
        userBorrow.setBook(entity);
        userBorrowRepository.save(userBorrow);
    }


    @Test
    void testFindByBookId_WithValidBookId_ReturnValidUser(){
        Optional<User> user = userRepository.findByBookId(1L);
        assertTrue(user.isPresent());
        assertEquals("test.test@gmail.com", user.get().getEmail());
        assertSame(this.user, user.get());
    }

    @Test
    void testFindByBookId_WithInvalidBookId_ReturnValidUser(){
        Optional<User> user = userRepository.findByBookId(-5L);
        assertTrue(user.isEmpty());
    }
}
