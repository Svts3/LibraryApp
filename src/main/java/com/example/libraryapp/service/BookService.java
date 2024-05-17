package com.example.libraryapp.service;

import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.BookStatus;
import com.example.libraryapp.model.Discount;

import java.util.Date;
import java.util.List;

public interface BookService extends GeneralService<Book, Long>{

    Book assignDiscountToBook(Long discountMeasureId, Double discountValue, Long bookId);

    Book findByTitle(String title);
    List<Book> findByBookStatus(String bookStatus);

    List<Book>findByCoverType(String coverType);

    List<Book>findByGenre(String genre);

    List<Book>findByDiscountValueAndMeasure(Double value, String measure);

    List<Book> findByUserId(Long userId);

    Book borrowBook(Long userId, Long bookId, Date issueDate, Date deadlineDate);

    Book returnBook(Long userId, Long bookId);

}
