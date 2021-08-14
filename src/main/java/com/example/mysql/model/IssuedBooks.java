package com.example.mysql.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
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
    private Date issueStartDate;
    private Date issueEndDate;


}
