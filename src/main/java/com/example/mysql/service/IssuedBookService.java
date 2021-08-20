package com.example.mysql.service;

import com.example.mysql.exception.NotFoundException;
import com.example.mysql.model.*;
import com.example.mysql.model.user.User;
import com.example.mysql.repository.IssuedBooksRepository;
import com.example.mysql.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class    IssuedBookService {
    @Autowired
    private final IssuedBooksRepository issuedBooksRepository;
    @Autowired
    private final UserService userService;
    @Autowired
    private final BookDBService bookDBService;
    @Autowired
    private final ReservationRepository reservationRepository;
    @Autowired
    private final ReservationService reservationService;

    public void issueBook(Book book, User user) {
        LocalDate issueStartDate = LocalDate.now();
        LocalDate issueEndDate = issueStartDate.plusDays(21);
        IssueStatus issueStatus = IssueStatus.ACTIVE;
        issuedBooksRepository.save(new IssuedBooks(issueStartDate, issueEndDate, user, book, issueStatus));
    }

    public void issueBookWithActiveReservation(Reservation reservation) {
        LocalDate issueStartDate = LocalDate.now();
        LocalDate issueEndDate = issueStartDate.plusDays(21);
        /*Optional<Reservation> currentReservation = reservationService.findReservationById(reservationId);
        Reservation reservation = new Reservation();
        if (currentReservation.isPresent()) {
             reservation = currentReservation.get();
        } else {
            throw new NotFoundException("No such reservation id");
        }*/
        Book currentBook = reservation.getBook();
        currentBook.setBookStatus(BookStatus.ISSUED);
        bookDBService.updateBook(currentBook);
        User currentUser = reservation.getUser();
        issuedBooksRepository.save(new IssuedBooks(issueStartDate, issueEndDate, currentUser, currentBook, IssueStatus.ACTIVE));


    }

    public List<IssuedBooks> showAllIssuedBooks() {
        return (List<IssuedBooks>) issuedBooksRepository.findAll();
    }

    public List<IssuedBooks> findIssuedBooksByUserId(Long userId) {
        return issuedBooksRepository.findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc(userId);
    }

    public void issueBookWithActiveReservation(Book book, User user) {
        LocalDate issueStartDate = LocalDate.now();
        LocalDate issueEndDate = issueStartDate.plusDays(21);

        book.setBookStatus(BookStatus.ISSUED);
        bookDBService.updateBook(book);
        issuedBooksRepository.save(new IssuedBooks(issueStartDate, issueEndDate, user, book, IssueStatus.ACTIVE));
    }
}
