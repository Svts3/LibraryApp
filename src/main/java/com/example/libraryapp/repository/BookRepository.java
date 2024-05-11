package com.example.libraryapp.repository;

import com.example.libraryapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book>findByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.bookStatus=:status")
    Collection<Book>findByBookStatus(@Param("status") String status);

    @Query("SELECT b FROM Book b WHERE b.coverType=:coverType")
    Collection<Book>findByCoverType(@Param("coverType")String coverType);

    @Query("SELECT b FROM Book b WHERE :genre IN (SELECT g.genreName FROM BookGenre g)")
    Collection<Book>findByGenre(@Param("genre") String genre);
    @Query("SELECT b FROM Book b WHERE b.discount.value=:value AND b.discount.discountMeasure=:measure")
    Collection<Book>findByDiscountValueAndMeasure(@Param("value")Double value,
                                                  @Param("measure")String measure);

    @Query("SELECT b FROM Book b JOIN UserBorrow ub on ub.book.id=b.id and ub.user.id=:userId")
    Collection<Book>findByUserId(@Param("userId")Long userId);






}
