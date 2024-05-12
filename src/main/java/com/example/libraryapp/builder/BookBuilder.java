package com.example.libraryapp.builder;

import com.example.libraryapp.model.*;

import java.util.Date;
import java.util.Set;

public interface BookBuilder extends GeneralBuilder<Book>{

    void setId(Long id);

    void setTitle(String title);

    void setSecurityDeposit(Double securityDeposit);

    void setValueRate(Integer valueRate);

    void setCreationDate(Date creationDate);

    void setLastModifiedDate(Date lastModifiedDate);

    void setAuthors(Set<Author> authors);

    void setGenres(Set<BookGenre>genres);

    void setBookStatus(BookStatus bookStatus);

    void setCoverType(CoverType coverType);

    void setDiscount(Discount discount);


}
