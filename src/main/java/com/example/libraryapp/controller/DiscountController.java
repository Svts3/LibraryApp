package com.example.libraryapp.controller;

import com.example.libraryapp.dto.DiscountDTO;
import com.example.libraryapp.mapper.DiscountMapper;
import com.example.libraryapp.model.Discount;
import com.example.libraryapp.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount")
public class DiscountController {

    private DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/")
    public ResponseEntity<List<DiscountDTO>> findAll() {
        List<Discount> discounts = discountService.findAll();
        List<DiscountDTO> discountDTOS = DiscountMapper.INSTANCE.discountsToDiscountDTOs(discounts);
        return ResponseEntity.ok(discountDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountDTO> findById(@PathVariable("id") Long id) {
        Discount discount = discountService.findById(id);
        DiscountDTO discountDTOS = DiscountMapper.INSTANCE.discountToDiscountDTO(discount);
        return ResponseEntity.ok(discountDTOS);
    }

    @PostMapping("/")
    public ResponseEntity<DiscountDTO> save(@RequestBody Discount discount) {
        Discount savedDiscount = discountService.save(discount);
        DiscountDTO discountDTOS = DiscountMapper.INSTANCE.discountToDiscountDTO(savedDiscount);
        return ResponseEntity.ok(discountDTOS);
    }

    @PostMapping("/book/{id}")
    public ResponseEntity<DiscountDTO>assignDiscountToBook(@RequestBody Discount discount,
                                                           @PathVariable("id") Long bookId){
        Discount savedDiscount = discountService.assignDiscountToBook(discount, bookId);
        DiscountDTO discountDTOS = DiscountMapper.INSTANCE.discountToDiscountDTO(savedDiscount);
        return ResponseEntity.ok(discountDTOS);

    }
    @PostMapping("/book/{id}/revoke")
    ResponseEntity<DiscountDTO> revokeDiscountFromBook(@PathVariable("id") Long bookId){
        Discount revokedDiscount = discountService.revokeDiscountFromBook(bookId);
        DiscountDTO discountDTOS = DiscountMapper.INSTANCE.discountToDiscountDTO(revokedDiscount);
        return ResponseEntity.ok(discountDTOS);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DiscountDTO>deleteById(@PathVariable("id")Long id){
        Discount deletedDiscount = discountService.deleteById(id);
        DiscountDTO discountDTOS = DiscountMapper.INSTANCE.discountToDiscountDTO(deletedDiscount);
        return ResponseEntity.ok(discountDTOS);
    }



}
