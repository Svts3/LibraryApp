package com.example.libraryapp.service;

import com.example.libraryapp.model.Discount;
import com.example.libraryapp.model.DiscountMeasure;
import org.springframework.data.repository.query.Param;

public interface DiscountService extends Findable<Discount, Long>, Persistable<Discount, Long> {

    Discount assignDiscountToBook(DiscountMeasure discountMeasure, Double discountValue, Long bookId);

    Discount revokeDiscountFromBook(Long bookId);

    Discount findByValueAndDiscountMeasure(@Param("value") Double value,
                                                     @Param("measure") String measure);
}
