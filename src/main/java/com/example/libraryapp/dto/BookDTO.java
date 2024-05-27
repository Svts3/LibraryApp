package com.example.libraryapp.dto;

import com.example.libraryapp.model.BookGenre;
import com.example.libraryapp.model.BookStatus;
import com.example.libraryapp.model.CoverType;
import com.example.libraryapp.model.Discount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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

    private Set<BookGenre>bookGenres;


}
