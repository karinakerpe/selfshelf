package com.example.mysql.controller;

import com.example.mysql.model.Book;
import com.example.mysql.model.Reservation;
import com.example.mysql.model.user.User;
import com.example.mysql.service.BookRecordService;
import com.example.mysql.service.IssuedBookService;
import com.example.mysql.service.ReservationService;
import com.example.mysql.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import static com.example.mysql.model.BookStatus.ISSUED;
import static com.example.mysql.model.BookStatus.RESERVED;

@RequiredArgsConstructor
@Controller
@RequestMapping("/reservation")
public class ReservationController {
    private final UserService userService;
    private final BookRecordService bookRecordService;
    private final ReservationService reservationService;

    private final Reservation reservation;
    private final IssuedBookService issuedBookService;


    @GetMapping("/{id}")
    public String makeReservation(@PathVariable("id") Long id, Model model, Principal principal){
        String currentUserEmail = principal.getName();
        User currentUser = userService.findUserByEmail(currentUserEmail);
        Book currentBook = bookRecordService.getBookById(id);
        currentBook.setBookStatus(RESERVED);
        reservationService.reserveBook(currentBook, currentUser);
        return "redirect:/books";

    }



    @GetMapping("/active_reservation")
    public String viewReservationsForUserId (Principal principal, Model model){
        String currentUserEmail = principal.getName();
        User currentUser = userService.findUserByEmail(currentUserEmail);
        Long currentUserId = currentUser.getId();
        List<Reservation> reservations = reservationService.findReservationByUserId(currentUserId);
        model.addAttribute("reservations", reservations);


        return "user_reservations";
    }

    @GetMapping("/active_reservation/all")
    public String viewAllActiveReservations (Principal principal, Model model){
        List<Reservation> reservations = reservationService.findAllActiveReservation(LocalDate.now());
        model.addAttribute("reservations", reservations);


        return "admin_reservations";
    }

    @GetMapping("/update_list")
    public String processRegistration() {
        reservationService.deleteReservationsExtendingEndDate(LocalDate.now());

        return "redirect:/reservation/active_reservation/all";
    }

    //////




}
