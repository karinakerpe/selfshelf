package com.example.mysql.model;

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
    private Long reservationId;
    private Long userId;
    private Long bookId;
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
}
