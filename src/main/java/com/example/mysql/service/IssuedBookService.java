package com.example.mysql.service;

import com.example.mysql.model.Book;
import com.example.mysql.model.BookStatus;
import com.example.mysql.model.IssuedBooks;
import com.example.mysql.model.Reservation;
import com.example.mysql.model.user.User;
import com.example.mysql.repository.IssuedBooksRepository;
import com.example.mysql.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public void issueBook(Book book, User user) {
        LocalDate issueStartDate = LocalDate.now();
        LocalDate issueEndDate = issueStartDate.plusDays(21);
        issuedBooksRepository.save(new IssuedBooks(issueStartDate, issueEndDate, user, book));
    }

    public void issueBookWithActiveReservation(Book book, User user, Long reservationId) {
        LocalDate issueStartDate = LocalDate.now();
        LocalDate issueEndDate = issueStartDate.plusDays(21);
        issuedBooksRepository.save(new IssuedBooks(issueStartDate, issueEndDate, user, book));
        reservationRepository.deleteById(reservationId);

    }

    public List<IssuedBooks> showAllIssuedBooks() {
        return (List<IssuedBooks>) issuedBooksRepository.findAll();
    }

    public List<IssuedBooks> findIssuedBooksByUserId(Long userId) {
        return issuedBooksRepository.findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc(userId);
    }

}
