package com.example.mysql.controller;

import com.example.mysql.model.Book;
import com.example.mysql.service.BookRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class BookController {

    private BookRecordService bookRecordService;

    public BookController(BookRecordService bookRecordService) {
        super();
        this.bookRecordService = bookRecordService;
    }

    // handler method to handle the list of books and return model and view???
    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", bookRecordService.getAllBooks());
        return "books";
    }

    @GetMapping("/book/new")
    public String createBookForm(Model model) {

        //create student object to hold student form data
        Book book = new Book();
        model.addAttribute("book", book);

        return "create_book";
    }

    @PostMapping("/books")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookRecordService.saveBook(book);

        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookRecordService.getBookById(id));
        return "edit_book";
    }

    @PostMapping("/books/{id}")
    public String updateBook(@PathVariable Long id,
                             @ModelAttribute("book") Book book,
                             Model model) {

        //get book from database by id
        Book existingBook = bookRecordService.getBookById(id);
        existingBook.setId(id);
        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());
        existingBook.setYear(book.getYear());
        existingBook.setPages(book.getPages());

        //save updated book object
        bookRecordService.updateBook(existingBook);
        return "redirect:/books";
    }

    @GetMapping("/books/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRecordService.deleteBookById(id);
        return "redirect:/books";
    }

}
