package com.example.mysql.model;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity (name = "Book")
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "pages", nullable = false)
    private Integer pages;
    @Column (name = "book_status")
    private BookStatus bookStatus;

    @Column(name= "isbn", nullable = false)
    private String isbn;

//    @ManyToOne
//    @JoinColumn(
//            name = "reservation_id",
//            referencedColumnName = "id",
//            foreignKey = @ForeignKey(
//                    name = "reservation_id_fk"
//            )
//    )
//    private Reservation reservation;

    @OneToMany(
            mappedBy = "book",

            fetch = FetchType.LAZY
    )
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(
            mappedBy = "book",

            fetch = FetchType.LAZY
    )
    private List<IssuedBooks> issuedBooks = new ArrayList<>();


    public Book() {

    }

    public Book(String title, String author, Integer year, Integer pages, String isbn, BookStatus bookStatus) {
        super();
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.isbn = isbn;
        this.bookStatus = bookStatus;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
