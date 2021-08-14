package com.example.mysql.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@RequiredArgsConstructor
@Table
@Entity
public class IssuedBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long issueId;
    private Long userId;
    private Long bookId;
    private LocalDate issueStartDate;
    private LocalDate issueEndDate;


}
