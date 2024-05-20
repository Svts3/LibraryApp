package com.example.libraryapp.builder.impl;

import com.example.libraryapp.builder.BookBuilder;
import com.example.libraryapp.model.*;

import java.util.Date;
import java.util.Set;

public class BookBuilderImpl implements BookBuilder {

    private Book book;

    public BookBuilderImpl() {
        this.book = new Book();
    }
    private BookBuilderImpl(Book book){
        this.book = book;
    }


    @Override
    public BookBuilder setId(Long id) {
        this.book.setId(id);
        return new BookBuilderImpl(book);
    }

    @Override
    public BookBuilder setTitle(String title) {
        this.book.setTitle(title);
        return new BookBuilderImpl(book);
    }

    @Override
    public BookBuilder setSecurityDeposit(Double securityDeposit) {
        this.book.setSecurityDeposit(securityDeposit);
        return new BookBuilderImpl(book);
    }

    @Override
    public BookBuilder setValueRate(Integer valueRate) {
        this.book.setValueRate(valueRate);
        return new BookBuilderImpl(book);
    }

    @Override
    public BookBuilder setCreationDate(Date creationDate) {
        this.book.setCreationDate(creationDate);
        return new BookBuilderImpl(book);
    }

    @Override
    public BookBuilder setLastModifiedDate(Date lastModifiedDate) {
        this.book.setLastModifiedDate(lastModifiedDate);
        return new BookBuilderImpl(book);
    }

    @Override
    public BookBuilder setAuthors(Set<Author> authors) {
        this.book.setAuthors(authors);
        return new BookBuilderImpl(book);
    }

    @Override
    public BookBuilder setGenres(Set<BookGenre> genres) {
        this.book.setGenres(genres);
        return new BookBuilderImpl(book);
    }

    @Override
    public BookBuilder setBookStatus(BookStatus bookStatus) {
        this.book.setBookStatus(bookStatus);
        return new BookBuilderImpl(book);
    }

    @Override
    public BookBuilder setCoverType(CoverType coverType) {
        this.book.setCoverType(coverType);
        return new BookBuilderImpl(book);
    }

    @Override
    public BookBuilder setDiscount(Discount discount) {
        this.book.setDiscount(discount);
        return new BookBuilderImpl(book);
    }

    @Override
    public Book build() {
        return book;
    }
}
