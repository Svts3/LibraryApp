package com.example.libraryapp.builder;

import com.example.libraryapp.model.*;

import java.util.Date;
import java.util.Set;

public interface BookBuilder extends GeneralBuilder<Book>{

    BookBuilder setId(Long id);

    BookBuilder setTitle(String title);

    BookBuilder setSecurityDeposit(Double securityDeposit);

    BookBuilder setValueRate(Integer valueRate);

    BookBuilder setCreationDate(Date creationDate);

    BookBuilder setLastModifiedDate(Date lastModifiedDate);

    BookBuilder setAuthors(Set<Author> authors);

    BookBuilder setGenres(Set<BookGenre>genres);

    BookBuilder setBookStatus(BookStatus bookStatus);

    BookBuilder setCoverType(CoverType coverType);

    BookBuilder setDiscount(Discount discount);


}
