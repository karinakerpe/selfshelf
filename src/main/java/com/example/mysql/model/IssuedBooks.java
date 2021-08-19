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
public class IssuedBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "reservation_id")
    private Long id;
    private LocalDate issueStartDate;
    private LocalDate issueEndDate;
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "issuing_user_id_fk"
            )
    )
    private User user;


    @ManyToOne
    @JoinColumn(
            name = "book_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "issued_book_id_fk"
            )
    )
    private Book book;


    public IssuedBooks(LocalDate issueStartDate, LocalDate issueEndDate, User user, Book book) {
        this.issueStartDate = issueStartDate;
        this.issueEndDate = issueEndDate;
        this.user = user;
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getIssueStartDate() {
        return issueStartDate;
    }

    public void setIssueStartDate(LocalDate issueStartDate) {
        this.issueStartDate = issueStartDate;
    }

    public LocalDate getIssueEndDate() {
        return issueEndDate;
    }

    public void setIssueEndDate(LocalDate issueEndDate) {
        this.issueEndDate = issueEndDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
