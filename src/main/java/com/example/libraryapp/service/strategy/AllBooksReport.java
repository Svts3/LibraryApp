package com.example.libraryapp.service.strategy;

import com.example.libraryapp.service.strategy.Report;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
public class AllBooksReport implements Report {
    private Long allBooksCount;
    private Double averagePrice;
    private Double maxPrice;
    private Double minPrice;

    public AllBooksReport(Long allBooksCount, Double averagePrice, Double maxPrice, Double minPrice) {
        this.allBooksCount = allBooksCount;
        this.averagePrice = averagePrice;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
    }
}
