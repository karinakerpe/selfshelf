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
    @Column (name = "reservation_id")
    private Long id;
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "user_id_fk"
            )
    )
    private User user;


    @ManyToOne
    @JoinColumn(
            name = "book_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "book_id_fk"
            )
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
