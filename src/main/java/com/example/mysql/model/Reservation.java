package com.example.mysql.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
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
    private Date reservationStartDate;
    private Date reservationEndDate;
}
