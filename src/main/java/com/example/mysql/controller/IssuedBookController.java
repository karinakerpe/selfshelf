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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/issued")
public class IssuedBookController {
    private final UserService userService;
    private final BookRecordService bookRecordService;
    private final ReservationService reservationService;
    private final IssuedBookService issuedBookService;
    @Autowired
    private final Reservation reservation;
    private final IssuedBooks issuedBooks;

    @GetMapping("/{id}") // Admin
    public String issueBook(@PathVariable("id") Long id, Model model, Principal principal) {

        Book currentBook = bookRecordService.getBookById(id);
        Reservation currentReservation = new Reservation();

        //Long reservationId = 0L;
        List<Reservation> reservationIdList = currentBook.getReservations();
        for (Reservation reservation : reservationIdList
        ) {
            currentReservation = reservation;
        }
        reservationIdList.clear();

        issuedBookService.issueBookWithActiveReservation(currentBook, currentReservation.getUser());
        reservationService.deleteByIdUpdatingBookStatus(currentReservation.getId());
        String currentUserEmail = principal.getName();
        User currentUser = userService.findUserByEmail(currentUserEmail);
        if (currentUser.getUserRole().name().equals("USER")){
        return "redirect:/books";
        }else{
            return "redirect:/issued/admin";
        }
    }

    @GetMapping("/admin")
    public String viewIssuedBooksForAdminId(Principal principal, Model model) {
        String currentUserEmail = principal.getName();
        User currentUser = userService.findUserByEmail(currentUserEmail);
        model.addAttribute("currentUserId", currentUser.getId());

        List<IssuedBooks> issuedBooks = issuedBookService.getIssuedBooksWithStatusActive();
        model.addAttribute("issuedBooks", issuedBooks);
        issuedBookService.showAllIssuedBooks();

        return "issued_admin";
    }

    @GetMapping("/user")
    public String viewIssuedBooksForUserId(Principal principal, Model model) {
        String currentUserEmail = principal.getName();
        User currentUser = userService.findUserByEmail(currentUserEmail);
        Long currentUserId = currentUser.getId();
        List<IssuedBooks> issuedBooksActive = issuedBookService.findIssuedBooksByUserIdActive(currentUserId);
        model.addAttribute("issuedBooksActive", issuedBooksActive);
        List<IssuedBooks> issuedBooksHistory = issuedBookService.findIssuedBooksByUserIdHistory(currentUserId);
        model.addAttribute("issuedBooksHistory", issuedBooksHistory);
        model.addAttribute("currentUserId", currentUserId);
        LocalDate localDate = LocalDate.now();
        model.addAttribute("date", localDate);
        return "issued_user";
    }

    @GetMapping("return/{id}") // Admin
    public String viewReturnDetails(@PathVariable("id") Long id, Model model, Principal principal) {
        IssuedBooks currentIssuedBook = issuedBookService.getIssuedBookById(id);
        model.addAttribute("currentIssueBook", currentIssuedBook);
        LocalDate planedReturnDate = currentIssuedBook.getIssueEndDate();
        model.addAttribute("planedReturnDate", planedReturnDate);
        LocalDate realReturnDate = LocalDate.now();
        model.addAttribute("realReturnDate", realReturnDate);
        int daysUntil = planedReturnDate.until(realReturnDate).getDays();
        model.addAttribute("daysOverdue", daysUntil);
        return "return_book";

    }

    @PostMapping("return/{id}") // Admin
    public String returnIssuedBookByIssueId(@PathVariable("id") Long id, Model model, Principal principal) {
//        IssuedBooks currentIssuedBook = issuedBookService.getIssuedBookById(id);
//        model.addAttribute("currentIssueBook", currentIssuedBook);
        LocalDate realReturnDate = LocalDate.now();
        issuedBookService.returnBook(id, realReturnDate);
        return "redirect:/issued/admin";

    }

}
