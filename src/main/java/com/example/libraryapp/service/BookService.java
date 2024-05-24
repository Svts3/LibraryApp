package com.example.libraryapp.service;

import com.example.libraryapp.model.Book;

import java.util.List;

public interface BookService extends Findable<Book, Long>, Persistable<Book, Long>,
        Updatable<Book, Long> {

    Book findByTitle(String title);

    List<Book> findByBookStatus(String bookStatus);

    List<Book> findByCoverType(String coverType);

    List<Book> findByGenre(String genre);

    List<Book> findByDiscountValueAndMeasure(Double value, String measure);

    List<Book> findByUserId(Long userId);


}
