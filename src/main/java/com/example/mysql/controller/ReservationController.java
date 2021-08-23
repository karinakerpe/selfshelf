package com.example.mysql.controller;

import com.example.mysql.model.Book;
import com.example.mysql.model.Reservation;
import com.example.mysql.model.user.User;
import com.example.mysql.service.BookRecordService;
import com.example.mysql.service.ReservationService;
import com.example.mysql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import static com.example.mysql.model.BookStatus.RESERVED;

@RequiredArgsConstructor
@Controller
@RequestMapping("/reservation")
public class ReservationController {
    private final UserService userService;
    private final BookRecordService bookRecordService;
    private final ReservationService reservationService;


    @GetMapping("/{id}")
    public String viewReservation(@PathVariable("id") Long id, Model model, Principal principal) {
        String currentUserEmail = principal.getName();
        User currentUser = userService.findUserByEmail(currentUserEmail);
        Book currentBook = bookRecordService.getBookById(id);
        model.addAttribute("book", currentBook);
        model.addAttribute("user", currentUser);
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(7);

        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("reservation", new Reservation());

        return "make_reservation";

    }

    @PostMapping("/{id}")
    public String makeReservation(@PathVariable("id") Long id, Principal principal) {
        String currentUserEmail = principal.getName();
        User currentUser = userService.findUserByEmail(currentUserEmail);
        Book currentBook = bookRecordService.getBookById(id);
        currentBook.setBookStatus(RESERVED);
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(7);
        reservationService.reserveBook(currentBook, currentUser, startDate, endDate);
        return "redirect:/books";

    }


    @GetMapping("/active_reservation")
    public String viewReservationsForUserId(Principal principal, Model model) {
        String currentUserEmail = principal.getName();
        User currentUser = userService.findUserByEmail(currentUserEmail);
        Long currentUserId = currentUser.getId();
        List<Reservation> reservations = reservationService.findReservationByUserId(currentUserId);
        model.addAttribute("reservations", reservations);
        model.addAttribute("id", currentUserId);

        return "user_reservations";
    }

    @GetMapping("/active_reservation/all")
    public String viewAllReservations(Principal principal, Model model) {
        List<Reservation> activeReservations = reservationService.findAllActiveReservation(LocalDate.now());
        model.addAttribute("activeReservations", activeReservations);
        List<Reservation> expiredReservations = reservationService.findAllExpiredReservation(LocalDate.now());
        model.addAttribute("expiredReservations", expiredReservations);
        model.addAttribute("date", LocalDate.now());
        String currentUserEmail = principal.getName();
        User currentUser = userService.findUserByEmail(currentUserEmail);
        model.addAttribute("id", currentUser.getId());

        return "admin_reservations";
    }

    @GetMapping("/update_list")
    public String processRegistration() {
        reservationService.deleteReservationsExtendingEndDate(LocalDate.now());

        return "redirect:/reservation/active_reservation/all";

    }

    @GetMapping("/delete/{id}") //user
    public String deleteById(@PathVariable("id") Long id, Principal principal) {
        reservationService.deleteReservationById(id);

        String currentUserEmail = principal.getName();
        User currentUser = userService.findUserByEmail(currentUserEmail);
        if (currentUser.getUserRole().name().equals("USER")){
            return "redirect:/reservation/active_reservation";
        }else{
            return "redirect:/reservation/active_reservation/all";
        }

    }

}
