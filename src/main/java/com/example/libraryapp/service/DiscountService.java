package com.example.libraryapp.service;

import com.example.libraryapp.model.Discount;
import com.example.libraryapp.model.DiscountMeasure;
import org.springframework.data.repository.query.Param;

public interface DiscountService extends Findable<Discount, Long>, Persistable<Discount, Long>{

    Discount assignDiscountToBook(Discount discount, Long bookId);

    Discount revokeDiscountFromBook(Long bookId);

    Discount findByValueAndDiscountMeasure(Double value, DiscountMeasure measure);
}
