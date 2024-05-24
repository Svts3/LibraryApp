package com.example.libraryapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserBorrowDTO {

    private Long id;

    private Date issueDate;

    private Date deadlineDate;

    private BookDTO book;


}
