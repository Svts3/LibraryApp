package com.example.libraryapp.repository;

import com.example.libraryapp.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Query("SELECT d FROM Discount d WHERE d.value=:value AND d.discountMeasure.measureName=:measure")
    Optional<Discount>findByValueAndDiscountMeasure(@Param("value") Double value,
                                                    @Param("measure") String measure);
}
