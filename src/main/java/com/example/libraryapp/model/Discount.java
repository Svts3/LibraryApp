package com.example.libraryapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "discounts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "value")
    private Double value;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "discount_measure_id", referencedColumnName = "id")
    private DiscountMeasure discountMeasure;

    public Discount(Double value, DiscountMeasure discountMeasure) {
        this.value = value;
        this.discountMeasure = discountMeasure;
    }
}
