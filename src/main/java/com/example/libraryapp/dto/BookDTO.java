package com.example.libraryapp.dto;

import com.example.libraryapp.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDTO {

    private Long id;

    private String title;

    private Double securityDeposit;

    private Integer valueRate;

    private Date creationDate;

    private Date lastModifiedDate;

    private DiscountDTO discount;

    private BookStatus bookStatus;

    private Set<BookGenre> genres;

    private Set<AuthorDTO> authors;


}
