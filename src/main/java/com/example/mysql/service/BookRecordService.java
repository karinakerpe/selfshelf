package com.example.mysql.service;

import com.example.mysql.model.Book;

import java.util.List;

public interface BookRecordService {
    List<Book> getAllBooks();

    Book saveBook(Book book);

    Book getBookById(Long id);

    Book updateBook(Book book);

    void deleteBookById(Long id);
}
