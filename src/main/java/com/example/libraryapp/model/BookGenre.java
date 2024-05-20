package com.example.libraryapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book_genres")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "genre_name")
    private String genreName;
}
