package com.example.mysql.controller;

import com.example.mysql.model.Book;
import com.example.mysql.model.user.User;
import com.example.mysql.service.BookRecordService;
import com.example.mysql.service.ReservationService;
import com.example.mysql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static com.example.mysql.model.BookStatus.RESERVED;

@RequiredArgsConstructor
@Controller
@RequestMapping("/reservation")
public class ReservationController {
    private final UserService userService;
    private final BookRecordService bookRecordService;
    private final ReservationService reservationService;


    @GetMapping("/{id}")
    public String makeReservation(@PathVariable("id") Long id, Model model, Principal principal){
        String currentUserEmail = principal.getName();
        User currentUser = userService.findUserByEmail(currentUserEmail);
        Long userId = currentUser.getId();
        Book currentBook = bookRecordService.getBookById(id);
        currentBook.setBookStatus(RESERVED);
        reservationService.reserveBook(id,userId);
        return "redirect:/books";

    }
}
