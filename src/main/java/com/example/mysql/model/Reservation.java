package com.example.mysql.model;

import com.example.mysql.model.user.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@RequiredArgsConstructor
@Table
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private User user;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(
            name = "book_id",
            referencedColumnName = "id"
    )
    private Book book;

//    private Long userId;
//    private Long bookId;


    public Reservation(LocalDate reservationStartDate, LocalDate reservationEndDate, User user, Book book) {
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
        this.user = user;
        this.book = book;
    }
}
