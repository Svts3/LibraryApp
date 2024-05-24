package com.example.libraryapp.dto;

import com.example.libraryapp.model.DiscountMeasure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DiscountDTO {

    private Long id;

    private Double value;

    private DiscountMeasure discountMeasure;

}
