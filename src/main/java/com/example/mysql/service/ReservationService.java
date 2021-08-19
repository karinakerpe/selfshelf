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

    public void reserveBook(Book book, User user) {
        LocalDate reservationStartDate = LocalDate.now();
        LocalDate reservationEndDate = LocalDate.now();

//        LocalDate reservationEndDate = reservationStartDate.plusDays(7);
        reservationRepository.save(new Reservation(reservationStartDate, reservationEndDate, user, book));
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


    public List<Reservation> findAllReservations (){
        return reservationRepository.findAll();
    }


    public void deleteReservationsExtendingEndDate (LocalDate date){
        List<Reservation> allReservations = reservationRepository.findAll();
        for (Reservation reservation :
                allReservations) {
            if(reservation.getReservationEndDate().equals(date)){
                Long id = reservation.getId();
                reservationRepository.deleteById(id);
                Book book = reservation.getBook();
                book.setBookStatus(BookStatus.AVAILABLE);
                bookDBService.updateBook(book);
            }

        }
    }
}
