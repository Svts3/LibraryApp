package com.example.libraryapp.repository;

import com.example.libraryapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.bookStatus=:status")
    List<Book> findByBookStatus(@Param("status") String status);

    @Query("SELECT b FROM Book b WHERE b.coverType=:coverType")
    List<Book> findByCoverType(@Param("coverType") String coverType);

    @Query("SELECT b FROM Book b WHERE :genre IN (SELECT g.genreName FROM BookGenre g)")
    List<Book> findByGenre(@Param("genre") String genre);

    @Query("SELECT b FROM Book b WHERE b.discount.value=:value AND b.discount.discountMeasure=:measure")
    List<Book> findByDiscountValueAndMeasure(@Param("value") Double value,
                                             @Param("measure") String measure);

    @Query("SELECT b FROM Book b JOIN UserBorrow ub on ub.book.id=b.id and ub.user.id=:userId")
    List<Book> findByUserId(@Param("userId") Long userId);


}
