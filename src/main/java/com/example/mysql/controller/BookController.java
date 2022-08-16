package com.example.mysql.controller;

import com.example.mysql.model.*;
import com.example.mysql.model.user.User;
import com.example.mysql.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@RequiredArgsConstructor
@Controller
@RequestMapping
public class BookController {
    @Autowired
    private BookRecordService bookRecordService;
    @Autowired
    private BookDBService bookDBService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private IssuedBookService issuedBookService;


    @Autowired
    private UserService service;

    @GetMapping("/books")
    public String listBooks(Model model, Principal principal) {
        String currentUserEmail = principal.getName();
        User currentUser = service.findUserByEmail(currentUserEmail);
        model.addAttribute("id", currentUser.getId());
        model.addAttribute("books", bookDBService.getAllAvailableBooks());
        model.addAttribute("allBooks", bookDBService.getAllBooks());
        return "books";
    }

    @GetMapping("/book-main")
    public String showMainBookPage(Model model) {
        model.addAttribute("books", bookRecordService.getAllBooks());
        return "books";
    }

    @GetMapping("/book/new")
    public String createBookForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "create_book";
    }

    @PostMapping("/books")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookDBService.saveBook(book);

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
        Book currentBook = bookRecordService.getBookById(id);
        bookRecordService.deleteBookById(id);
        return "redirect:/books";
    }

    @GetMapping("/books/info/{id}")
    public String getBookInfo(@PathVariable Long id, Model model) {
        Book currentBook = bookRecordService.getBookById(id);
        model.addAttribute("books", currentBook);
        List<Reservation> reservations = reservationService.findReservationByBookId(id);
        model.addAttribute("reservations", reservations);
        List<IssuedBooks> issues = issuedBookService.findIssueBooksByBookIdWithIssueStatusActive(id);
        model.addAttribute("issues", issues);
        List<IssuedBooks> issueHistory = issuedBookService.findIssueBooksByBookIdWithIssueStatusHistory(id);
        model.addAttribute("history", issueHistory);

        return "books_view_info";
    }


    @GetMapping(value = "/books/search")
    public String search(BookSearch bookSearch, Model model) {
        model.addAttribute("pageName", "Book Search");
        return "search";
    }


    @PostMapping(value = "/books/search")
    public String getSearchedBook(@Valid BookSearch bookSearch, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "result";
        }
        List<Book> books = bookRecordService.search(bookSearch);
        model.addAttribute("books", books);
        return "result";
    }


    @GetMapping(value = "/books/faq")
    public String faq(BookSearch bookSearch, Model model, Principal principal) {
        String currentUserEmail = principal.getName();
        User currentUser = service.findUserByEmail(currentUserEmail);
        model.addAttribute("id", currentUser.getId());
        model.addAttribute("pageName", "Book Search");
        return "faq";
    }
}
