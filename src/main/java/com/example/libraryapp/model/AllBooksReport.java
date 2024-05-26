package com.example.libraryapp.model;

import com.example.libraryapp.model.interfaces.Report;
import com.example.libraryapp.service.BookService;
import com.example.libraryapp.service.ReportService;
import lombok.AllArgsConstructor;
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
