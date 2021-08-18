package com.example.mysql.service;

import com.example.mysql.model.Book;
import com.example.mysql.model.Reservation;
import com.example.mysql.model.user.User;
import com.example.mysql.repository.BookRepository;
import com.example.mysql.repository.ReservationRepository;
import com.example.mysql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class ReservationService {
    @Autowired
    private final ReservationRepository reservationRepository;
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final UserRepository userRepository;

    public void reserveBook (Book book, User user) {
        LocalDate reservationStartDate = LocalDate.now();
        LocalDate reservationEndDate = reservationStartDate.plusDays(7);
        reservationRepository.save(new Reservation(reservationStartDate, reservationEndDate, user, book));
    }
}
