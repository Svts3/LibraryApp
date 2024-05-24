package com.example.libraryapp.service.impl;

import com.example.libraryapp.exception.DiscountNotFoundException;
import com.example.libraryapp.exception.DiscountRevokeException;
import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.Discount;
import com.example.libraryapp.model.DiscountMeasure;
import com.example.libraryapp.repository.DiscountRepository;
import com.example.libraryapp.service.BookService;
import com.example.libraryapp.service.DiscountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {

    private DiscountRepository discountRepository;

    private BookService bookService;

    @Autowired
    public DiscountServiceImpl(DiscountRepository discountRepository, BookService bookService) {
        this.discountRepository = discountRepository;
        this.bookService = bookService;
    }

    @Transactional
    @Override
    public Discount assignDiscountToBook(Discount discount, Long bookId) {
        Optional<Discount> discountOptional = discountRepository
                .findByValueAndDiscountMeasure(discount.getValue(),
                        discount.getDiscountMeasure());

        if (discountOptional.isEmpty()) {
            discountOptional = Optional.of(discountRepository.save(new Discount(discount.getValue(),
                    discount.getDiscountMeasure())));
        }

        Book book = bookService.findById(bookId);
        book.setDiscount(discountOptional.get());
        return discountOptional.get();
    }

    @Transactional
    @Override
    public Discount revokeDiscountFromBook(Long bookId) {
        Book book = bookService.findById(bookId);
        if(book.getDiscount()==null){
            throw new DiscountRevokeException(String.format("No discounts are assigned to the Book[ID=%d]",
                    bookId));
        }
        Discount discount = book.getDiscount();
        book.setDiscount(null);
        bookService.save(book);
        return discount;
    }

    @Override
    public Discount findByValueAndDiscountMeasure(Double value, DiscountMeasure measure) {
        return discountRepository.findByValueAndDiscountMeasure(value, measure).orElseThrow(
                ()->new DiscountNotFoundException(
                        String.format("Discount[value=%f, measure=%s] was not found!", value, measure)));
    }

    @Override
    public List<Discount> findAll() {
        return discountRepository.findAll();
    }

    @Override
    public Discount save(Discount entity) {
        return discountRepository.save(entity);
    }

    @Override
    public Discount findById(Long aLong) {
        return discountRepository.findById(aLong).orElseThrow(
                ()->new DiscountNotFoundException(String.format("Discount[ID=%d] was not found!", aLong)));
    }
    @Transactional
    @Override
    public Discount deleteById(Long aLong) {
        Discount discount = this.findById(aLong);
        discountRepository.deleteById(aLong);
        return discount;
    }
}
