package com.example.libraryapp.repository;

import com.example.libraryapp.model.CoverType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoverTypeRepository extends JpaRepository<CoverType, Long> {

    Optional<CoverType>findByCoverType(String coverType);
}
