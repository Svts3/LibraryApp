package com.example.libraryapp.repository;

import com.example.libraryapp.model.DiscountMeasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountMeasureRepository extends JpaRepository<DiscountMeasure, Long> {

    Optional<DiscountMeasure>findByMeasureName(String measureName);

}
