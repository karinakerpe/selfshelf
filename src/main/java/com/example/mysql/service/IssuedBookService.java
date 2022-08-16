package com.example.mysql.service;

import com.example.mysql.model.*;
import com.example.mysql.model.user.User;
import com.example.mysql.repository.IssuedBooksRepository;
import com.example.mysql.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.mysql.model.IssueStatus.ACTIVE;
import static com.example.mysql.model.IssueStatus.HISTORY;

@RequiredArgsConstructor
@Service
public class IssuedBookService {
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
        issuedBooksRepository.save(new IssuedBooks(issueStartDate, issueEndDate, user, book, IssueStatus.ACTIVE));
    }

    public List<IssuedBooks> showAllIssuedBooks() {
        return issuedBooksRepository.findAll();
    }

    public List<IssuedBooks> findIssuedBooksByUserIdActive(Long userId) {
        List<IssuedBooks> books = issuedBooksRepository.findAllByUserIdEquals(userId);
        return books.stream()
                .filter(issuedBooks -> issuedBooks.getBook().getBookStatus().equals(IssueStatus.ACTIVE) )
                .collect(Collectors.toList());

    }

    public List<IssuedBooks> findIssuedBooksByUserIdHistory(Long userId) {
        List <IssuedBooks> issuesWithHistory = new ArrayList<>();
        List <IssuedBooks> allIssues = issuedBooksRepository.findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc(userId);
        for (IssuedBooks issueBook: allIssues) {
            if(issueBook.getIssueStatus().equals(HISTORY)){
                issuesWithHistory.add(issueBook);
            }

        }
        return issuesWithHistory;    }

    public void issueBookWithActiveReservation(Book book, User user) {
        LocalDate issueStartDate = LocalDate.now().minusDays(30);
        LocalDate issueEndDate = issueStartDate.plusDays(21);
        book.setBookStatus(BookStatus.ISSUED);
        bookDBService.updateBook(book);
        issuedBooksRepository.save(new IssuedBooks(issueStartDate, issueEndDate, user, book, IssueStatus.ACTIVE));
    }

    public List<IssuedBooks> findIssuedBooksByBookId(Long bookId) {
        return issuedBooksRepository.findIssuedBooksByBookIdEqualsOrderByIssueEndDateAsc(bookId);
    }


    public List<IssuedBooks> findIssueBooksByBookIdWithIssueStatusHistory(Long bookId) {
        List <IssuedBooks> issuesWithHistory = new ArrayList<>();
        List <IssuedBooks> allIssues = findIssuedBooksByBookId(bookId);
        for (IssuedBooks issueBook: allIssues) {
            if(issueBook.getIssueStatus().equals(HISTORY)){
                issuesWithHistory.add(issueBook);
            }

        }
        return issuesWithHistory;
    }

    public List<IssuedBooks> findIssueBooksByBookIdWithIssueStatusActive(Long bookId) {
        List <IssuedBooks> issuesWithActive = new ArrayList<>();
        List <IssuedBooks> allIssues = findIssuedBooksByBookId(bookId);
        for (IssuedBooks issueBook: allIssues) {
            if(issueBook.getIssueStatus().equals(ACTIVE)){
                issuesWithActive.add(issueBook);
            }

        }

        return issuesWithActive;
    }

public IssuedBooks getIssuedBookById (Long issueId){
        return issuedBooksRepository.getById(issueId);
}

    public void returnBook(Long issueId, LocalDate realReturnDate) {
    IssuedBooks issue = issuedBooksRepository.getById(issueId);
    Book book = issue.getBook();
    book.setBookStatus(BookStatus.AVAILABLE);
    bookDBService.updateBook(book);
    issue.setIssueEndDate(realReturnDate);
    issue.setIssueStatus(HISTORY);
    issuedBooksRepository.save(issue);
    }



    public List<IssuedBooks> getIssuedBooksWithStatusActive (){
        return issuedBooksRepository.findIssuedBooksByIssueStatusEqualsOrderByIssueEndDateDesc(ACTIVE);
    }
}
