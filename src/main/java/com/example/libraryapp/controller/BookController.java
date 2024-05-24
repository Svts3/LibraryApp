package com.example.libraryapp.controller;

import com.example.libraryapp.dto.BookDTO;
import com.example.libraryapp.mapper.BookMapper;
import com.example.libraryapp.model.Book;
import com.example.libraryapp.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public ResponseEntity<List<BookDTO>> findAll() {
        List<Book> books = bookService.findAll();
        List<BookDTO> bookDTOS = BookMapper.INSTANCE.booksToBookDTOs(books);
        return ResponseEntity.ok(bookDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO>findById(@PathVariable("id")Long id){
        Book book = bookService.findById(id);
        BookDTO bookDTO = BookMapper.INSTANCE.bookToBookDTO(book);
        return ResponseEntity.ok(bookDTO);
    }

    @PostMapping("/")
    public ResponseEntity<BookDTO>save(@RequestBody Book book){
        Book savedBook = bookService.save(book);
        BookDTO bookDTO = BookMapper.INSTANCE.bookToBookDTO(savedBook);
        return ResponseEntity.ok(bookDTO);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<BookDTO>update(@PathVariable("id")Long id, @RequestBody Book book){
       Book updatedBook = bookService.update(book, id);
        BookDTO bookDTO = BookMapper.INSTANCE.bookToBookDTO(updatedBook);
        return ResponseEntity.ok(bookDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO>deleteById(@PathVariable("id")Long id){
        Book book = bookService.deleteById(id);
        BookDTO bookDTO = BookMapper.INSTANCE.bookToBookDTO(book);
        return ResponseEntity.ok(bookDTO);
    }









}
