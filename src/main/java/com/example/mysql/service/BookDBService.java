package com.example.mysql.service;

import com.example.mysql.model.Book;
import com.example.mysql.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDBService implements BookRecordService {

    private BookRepository bookRepository;

    public BookDBService(BookRepository bookRepository) {
        super();
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }


}
