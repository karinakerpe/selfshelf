package com.example.mysql.controller;

import com.example.mysql.model.Book;
import com.example.mysql.model.IssuedBooks;
import com.example.mysql.model.Reservation;
import com.example.mysql.model.user.User;
import com.example.mysql.service.BookRecordService;
import com.example.mysql.service.IssuedBookService;
import com.example.mysql.service.ReservationService;
import com.example.mysql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

import static com.example.mysql.model.BookStatus.ISSUED;
import static com.example.mysql.model.BookStatus.RESERVED;

@RequiredArgsConstructor
@Controller
@RequestMapping("/issued")
public class IssuedBookController {
    private final UserService userService;
    private final BookRecordService bookRecordService;
    private final ReservationService reservationService;
    private final IssuedBookService issuedBookService;
    private final Reservation reservation;
    private final IssuedBooks issuedBooks;

    @GetMapping("/{id}") // Admin
    public String issueBook (@PathVariable("id") Long id, Model model, Principal principal) {
        Long reservationId = reservation.getId();
        Book currentBook = bookRecordService.getBookById(id);
        //String currentUserEmail = principal.getName();
        //User currentUser = userService.findUserByEmail(currentUserEmail);

        User bookUser = reservation.getUser();

        //issuedBooks.setUser(bookUser);
        currentBook.setBookStatus(ISSUED);
        issuedBookService.issueBookWithActiveReservation(currentBook, bookUser, reservationId);
        return "redirect:/books";

    }

    @GetMapping("/admin")
    public String viewIssuedBooksForAdminId (Principal principal, Model model){
        String currentUserEmail = principal.getName();
        User currentUser = userService.findUserByEmail(currentUserEmail);
        Long currentUserId = currentUser.getId();
        List<IssuedBooks> issuedBooks = issuedBookService.findIssuedBooksByUserId(currentUserId);
        model.addAttribute("issuedBooks", issuedBooks);

        //List<IssuedBooks> issuedBooks = issuedBookService.showAllIssuedBooks();
        //model.addAttribute("issuedBooks", issuedBooks);
        issuedBookService.showAllIssuedBooks();

        return "issued_admin";
    }

    @GetMapping("/user")
    public String viewIssuedBooksForUserId (Principal principal, Model model){
        String currentUserEmail = principal.getName();
        User currentUser = userService.findUserByEmail(currentUserEmail);
        Long currentUserId = currentUser.getId();
        List<IssuedBooks> issuedBooks = issuedBookService.findIssuedBooksByUserId(currentUserId);
        model.addAttribute("issuedBooks", issuedBooks);


        return "issued_user";
    }





}
