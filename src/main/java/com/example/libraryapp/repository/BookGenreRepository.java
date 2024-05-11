package com.example.libraryapp.repository;

import com.example.libraryapp.model.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookGenreRepository extends JpaRepository<BookGenre, Long> {

    Optional<BookGenre> findByGenreName(String genreName);

}
