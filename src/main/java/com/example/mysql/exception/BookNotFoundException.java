package com.example.mysql.exception;

import com.example.mysql.model.Book;
import lombok.Getter;


public class BookNotFoundException extends Exception {
    @Getter
    Book book;


    public BookNotFoundException(String message) {
        super(message);
    }


    public BookNotFoundException(Book book) {
        this.book=book;
    }

}
