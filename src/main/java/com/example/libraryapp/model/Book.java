package com.example.libraryapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "security_deposit")
    private Double securityDeposit;

    @Column(name = "value_rate")
    private Integer valueRate;

    @CreatedDate
    @Column(name = "creation_date")
    private Date creationDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors;

    @ManyToMany
    @JoinTable(name = "books_genres", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    private Set<BookGenre> genres;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private BookStatus bookStatus;

    @ManyToOne
    @JoinColumn(name = "cover_type_id", referencedColumnName = "id")
    private CoverType coverType;

    @ManyToOne
    @JoinColumn(name = "discount_id", referencedColumnName = "id")
    private Discount discount;

}
