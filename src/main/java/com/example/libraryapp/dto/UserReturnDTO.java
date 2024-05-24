package com.example.libraryapp.dto;

import com.example.libraryapp.model.Book;
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
public class UserReturnDTO {

    private Long id;

    private Date returnDate;

    private BookDTO book;

}
