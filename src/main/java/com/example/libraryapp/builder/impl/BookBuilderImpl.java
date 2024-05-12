package com.example.libraryapp.builder.impl;

import com.example.libraryapp.builder.BookBuilder;
import com.example.libraryapp.model.*;

import java.util.Date;
import java.util.Set;

public class BookBuilderImpl implements BookBuilder {

    private Book book;

    public BookBuilderImpl(Book book) {
        this.book = book;
    }

    @Override
    public void setId(Long id) {
        this.book.setId(id);
    }

    @Override
    public void setTitle(String title) {
        this.book.setTitle(title);
    }

    @Override
    public void setSecurityDeposit(Double securityDeposit) {
        this.book.setSecurityDeposit(securityDeposit);
    }

    @Override
    public void setValueRate(Integer valueRate) {
        this.book.setValueRate(valueRate);
    }

    @Override
    public void setCreationDate(Date creationDate) {
        this.book.setCreationDate(creationDate);
    }

    @Override
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.book.setLastModifiedDate(lastModifiedDate);
    }

    @Override
    public void setAuthors(Set<Author> authors) {
        this.book.setAuthors(authors);
    }

    @Override
    public void setGenres(Set<BookGenre> genres) {
        this.book.setGenres(genres);
    }

    @Override
    public void setBookStatus(BookStatus bookStatus) {
        this.book.setBookStatus(bookStatus);
    }

    @Override
    public void setCoverType(CoverType coverType) {
        this.book.setCoverType(coverType);
    }

    @Override
    public void setDiscount(Discount discount) {
        this.book.setDiscount(discount);
    }

    @Override
    public Book build() {
        return book;
    }
}
