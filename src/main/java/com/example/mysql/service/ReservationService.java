package com.example.mysql.service;

import com.example.mysql.model.Book;
import com.example.mysql.model.BookStatus;
import com.example.mysql.model.Reservation;
import com.example.mysql.model.user.User;
import com.example.mysql.repository.BookRepository;
import com.example.mysql.repository.ReservationRepository;
import com.example.mysql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservationService {
    @Autowired
    private final ReservationRepository reservationRepository;
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final UserRepository userRepository;

    private final BookDBService bookDBService;

    public void reserveBook(Book book, User user, LocalDate startDate, LocalDate endDate) {

        reservationRepository.save(new Reservation(startDate, endDate, user, book));
    }

    public List<Reservation> findReservationByUserId(Long userId) {
        return reservationRepository.findReservationsByUserIdEqualsOrderByReservationStartDateAsc(userId);
    }

    public List<Reservation> findReservationByBookId(Long bookId) {
        return reservationRepository.findReservationsByBookIdEquals(bookId);
    }
    public List<Reservation> findAllActiveReservation (LocalDate date) {
        return reservationRepository.findReservationsByReservationEndDateAfter(date);
    }

    public List<Reservation> findAllExpiredReservation (LocalDate date){
        return reservationRepository.findReservationsByReservationEndDateBefore(date);
    }


    public List<Reservation> findAllReservations (){
        return reservationRepository.findAll();
    }


    public void deleteReservationsExtendingEndDate (LocalDate date){
        List<Reservation> allReservations = reservationRepository.findAll();
        for (Reservation reservation :
                allReservations) {
            if(reservation.getReservationEndDate().isBefore(date)){
                Long id = reservation.getId();
                reservationRepository.deleteById(id);
                Book book = reservation.getBook();
                book.setBookStatus(BookStatus.AVAILABLE);
                bookDBService.updateBook(book);
            }

        }
    }

    public Optional<Reservation> findReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public void deleteByIdUpdatingBookStatus(Long id) {

        reservationRepository.deleteById(id);
    }
}
