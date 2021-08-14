package com.example.mysql.service;

import com.example.mysql.model.Reservation;
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

    public void reserveBook (Long bookId, Long userId) {
        LocalDate reservationStartDate = LocalDate.now();
        LocalDate reservationEndDate = reservationStartDate.plusWeeks(2);
        Reservation newReservation = new Reservation();
        newReservation.setReservationStartDate(reservationStartDate);
        newReservation.setReservationEndDate(reservationEndDate);
        newReservation.setBookId(bookId);
        newReservation.setUserId(userId);
        reservationRepository.save(newReservation);
    }
}
