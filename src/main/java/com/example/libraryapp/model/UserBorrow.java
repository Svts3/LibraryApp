package com.example.libraryapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "user_borrows")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserBorrow {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "deadline_date")
    private Date deadlineDate;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

}
