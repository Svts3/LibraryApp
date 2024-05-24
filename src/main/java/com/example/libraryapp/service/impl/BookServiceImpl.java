package com.example.libraryapp.service.impl;

import com.example.libraryapp.exception.BookNotFoundException;
import com.example.libraryapp.mapper.BookMapper;
import com.example.libraryapp.model.Book;
import com.example.libraryapp.repository.BookRepository;
import com.example.libraryapp.service.AuthorService;
import com.example.libraryapp.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;


    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional
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
        BookMapper.INSTANCE.updateBook(book, entity);
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
