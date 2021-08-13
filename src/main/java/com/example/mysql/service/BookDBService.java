package com.example.mysql.service;

import com.example.mysql.model.Book;
import com.example.mysql.model.BookSearch;
import com.example.mysql.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.matchingAll;

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
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> search(BookSearch bookSearch) {
        Book book = new Book();
        book.setTitle(bookSearch.getTitle());
        book.setAuthor(bookSearch.getAuthor());
        book.setYear(bookSearch.getYear());
        book.setPages(bookSearch.getPages());

        Example<Book> bookExample = Example.of(book, matchingAll().withIgnoreNullValues());
        return bookRepository.findAll(bookExample);
    }
    }


