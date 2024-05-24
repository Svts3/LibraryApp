package com.example.libraryapp.dto;

import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.DiscountMeasure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AssignDiscountRequest {

    private  DiscountMeasure discountMeasure;

    private Double discountValue;

    private Book book;
}
