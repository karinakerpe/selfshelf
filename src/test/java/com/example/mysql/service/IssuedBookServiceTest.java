package com.example.mysql.service;

import com.example.mysql.model.Book;
import com.example.mysql.model.BookStatus;
import com.example.mysql.model.IssueStatus;
import com.example.mysql.model.IssuedBooks;
import com.example.mysql.model.user.User;
import com.example.mysql.model.user.UserRole;
import com.example.mysql.repository.IssuedBooksRepository;
import com.example.mysql.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {IssuedBookService.class})
@ExtendWith(SpringExtension.class)
class IssuedBookServiceTest {
    @MockBean
    private BookDBService bookDBService;

    @Autowired
    private IssuedBookService issuedBookService;

    @MockBean
    private IssuedBooksRepository issuedBooksRepository;

    @MockBean
    private ReservationRepository reservationRepository;

    @MockBean
    private ReservationService reservationService;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link IssuedBookService#issueBook(Book, User)}
     */
    @Test
    void testIssueBook() {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setId(123L);
        book.setIsbn("Isbn");
        book.setIssuedBooks(new ArrayList<>());
        book.setPages(1);
        book.setReservations(new ArrayList<>());
        book.setTitle("Dr");
        book.setYear(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setReservations(new ArrayList<>());
        user.setResetPasswordToken("ABC123");
        user.setUserRole(UserRole.ADMIN);

        IssuedBooks issuedBooks = new IssuedBooks();
        issuedBooks.setBook(book);
        issuedBooks.setId(123L);
        issuedBooks.setIssueEndDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStartDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStatus(IssueStatus.ACTIVE);
        issuedBooks.setUser(user);
        when(this.issuedBooksRepository.save((IssuedBooks) any())).thenReturn(issuedBooks);

        Book book1 = new Book();
        book1.setAuthor("JaneDoe");
        book1.setBookStatus(BookStatus.AVAILABLE);
        book1.setId(123L);
        book1.setIsbn("Isbn");
        book1.setIssuedBooks(new ArrayList<>());
        book1.setPages(1);
        book1.setReservations(new ArrayList<>());
        book1.setTitle("Dr");
        book1.setYear(1);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setReservations(new ArrayList<>());
        user1.setResetPasswordToken("ABC123");
        user1.setUserRole(UserRole.ADMIN);
        this.issuedBookService.issueBook(book1, user1);
        verify(this.issuedBooksRepository).save((IssuedBooks) any());
    }

    /**
     * Method under test: {@link IssuedBookService#showAllIssuedBooks()}
     */
    @Test
    void testShowAllIssuedBooks() {
        ArrayList<IssuedBooks> issuedBooksList = new ArrayList<>();
        when(this.issuedBooksRepository.findAll()).thenReturn(issuedBooksList);
        List<IssuedBooks> actualShowAllIssuedBooksResult = this.issuedBookService.showAllIssuedBooks();
        assertSame(issuedBooksList, actualShowAllIssuedBooksResult);
        assertTrue(actualShowAllIssuedBooksResult.isEmpty());
        verify(this.issuedBooksRepository).findAll();
    }

    /**
     * Method under test: {@link IssuedBookService#showAllIssuedBooks()}
     */
    @Test
    void testShowAllIssuedBooks2() {
        ArrayList<IssuedBooks> issuedBooksList = new ArrayList<>();
        when(this.issuedBooksRepository.findAll()).thenReturn(issuedBooksList);
        List<IssuedBooks> actualShowAllIssuedBooksResult = this.issuedBookService.showAllIssuedBooks();
        assertSame(issuedBooksList, actualShowAllIssuedBooksResult);
        assertTrue(actualShowAllIssuedBooksResult.isEmpty());
        verify(this.issuedBooksRepository).findAll();
    }

    /**
     * Method under test: {@link IssuedBookService#findIssuedBooksByUserIdHistory(Long)}
     */
    @Test
    void testFindIssuedBooksByUserIdHistory() {
        when(this.issuedBooksRepository.findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any()))
                .thenReturn(new ArrayList<>());
        assertTrue(this.issuedBookService.findIssuedBooksByUserIdHistory(123L).isEmpty());
        verify(this.issuedBooksRepository).findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any());
    }

    /**
     * Method under test: {@link IssuedBookService#findIssuedBooksByUserIdHistory(Long)}
     */
    @Test
    void testFindIssuedBooksByUserIdHistory2() {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setId(123L);
        book.setIsbn("Isbn");
        book.setIssuedBooks(new ArrayList<>());
        book.setPages(1);
        book.setReservations(new ArrayList<>());
        book.setTitle("Dr");
        book.setYear(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setReservations(new ArrayList<>());
        user.setResetPasswordToken("ABC123");
        user.setUserRole(UserRole.ADMIN);

        IssuedBooks issuedBooks = new IssuedBooks();
        issuedBooks.setBook(book);
        issuedBooks.setId(123L);
        issuedBooks.setIssueEndDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStartDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStatus(IssueStatus.ACTIVE);
        issuedBooks.setUser(user);

        ArrayList<IssuedBooks> issuedBooksList = new ArrayList<>();
        issuedBooksList.add(issuedBooks);
        when(this.issuedBooksRepository.findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any()))
                .thenReturn(issuedBooksList);
        assertTrue(this.issuedBookService.findIssuedBooksByUserIdHistory(123L).isEmpty());
        verify(this.issuedBooksRepository).findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any());
    }

    /**
     * Method under test: {@link IssuedBookService#findIssuedBooksByUserIdHistory(Long)}
     */
    @Test
    void testFindIssuedBooksByUserIdHistory3() {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setId(123L);
        book.setIsbn("Isbn");
        book.setIssuedBooks(new ArrayList<>());
        book.setPages(1);
        book.setReservations(new ArrayList<>());
        book.setTitle("Dr");
        book.setYear(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setReservations(new ArrayList<>());
        user.setResetPasswordToken("ABC123");
        user.setUserRole(UserRole.ADMIN);
        IssuedBooks issuedBooks = mock(IssuedBooks.class);
        when(issuedBooks.getIssueStatus()).thenReturn(IssueStatus.HISTORY);
        doNothing().when(issuedBooks).setBook((Book) any());
        doNothing().when(issuedBooks).setId((Long) any());
        doNothing().when(issuedBooks).setIssueEndDate((LocalDate) any());
        doNothing().when(issuedBooks).setIssueStartDate((LocalDate) any());
        doNothing().when(issuedBooks).setIssueStatus((IssueStatus) any());
        doNothing().when(issuedBooks).setUser((User) any());
        issuedBooks.setBook(book);
        issuedBooks.setId(123L);
        issuedBooks.setIssueEndDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStartDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStatus(IssueStatus.ACTIVE);
        issuedBooks.setUser(user);

        ArrayList<IssuedBooks> issuedBooksList = new ArrayList<>();
        issuedBooksList.add(issuedBooks);
        when(this.issuedBooksRepository.findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any()))
                .thenReturn(issuedBooksList);
        assertEquals(1, this.issuedBookService.findIssuedBooksByUserIdHistory(123L).size());
        verify(this.issuedBooksRepository).findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any());
        verify(issuedBooks).getIssueStatus();
        verify(issuedBooks).setBook((Book) any());
        verify(issuedBooks).setId((Long) any());
        verify(issuedBooks).setIssueEndDate((LocalDate) any());
        verify(issuedBooks).setIssueStartDate((LocalDate) any());
        verify(issuedBooks).setIssueStatus((IssueStatus) any());
        verify(issuedBooks).setUser((User) any());
    }

    /**
     * Method under test: {@link IssuedBookService#findIssuedBooksByUserIdHistory(Long)}
     */
    @Test
    void testFindIssuedBooksByUserIdHistory4() {
        when(this.issuedBooksRepository.findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any()))
                .thenReturn(new ArrayList<>());
        assertTrue(this.issuedBookService.findIssuedBooksByUserIdHistory(123L).isEmpty());
        verify(this.issuedBooksRepository).findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any());
    }

    /**
     * Method under test: {@link IssuedBookService#findIssuedBooksByUserIdHistory(Long)}
     */
    @Test
    void testFindIssuedBooksByUserIdHistory5() {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setId(123L);
        book.setIsbn("Isbn");
        book.setIssuedBooks(new ArrayList<>());
        book.setPages(1);
        book.setReservations(new ArrayList<>());
        book.setTitle("Dr");
        book.setYear(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setReservations(new ArrayList<>());
        user.setResetPasswordToken("ABC123");
        user.setUserRole(UserRole.ADMIN);

        IssuedBooks issuedBooks = new IssuedBooks();
        issuedBooks.setBook(book);
        issuedBooks.setId(123L);
        issuedBooks.setIssueEndDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStartDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStatus(IssueStatus.ACTIVE);
        issuedBooks.setUser(user);

        ArrayList<IssuedBooks> issuedBooksList = new ArrayList<>();
        issuedBooksList.add(issuedBooks);
        when(this.issuedBooksRepository.findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any()))
                .thenReturn(issuedBooksList);
        assertTrue(this.issuedBookService.findIssuedBooksByUserIdHistory(123L).isEmpty());
        verify(this.issuedBooksRepository).findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any());
    }

    /**
     * Method under test: {@link IssuedBookService#findIssuedBooksByUserIdHistory(Long)}
     */
    @Test
    void testFindIssuedBooksByUserIdHistory6() {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setId(123L);
        book.setIsbn("Isbn");
        book.setIssuedBooks(new ArrayList<>());
        book.setPages(1);
        book.setReservations(new ArrayList<>());
        book.setTitle("Dr");
        book.setYear(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setReservations(new ArrayList<>());
        user.setResetPasswordToken("ABC123");
        user.setUserRole(UserRole.ADMIN);
        IssuedBooks issuedBooks = mock(IssuedBooks.class);
        when(issuedBooks.getIssueStatus()).thenReturn(IssueStatus.HISTORY);
        doNothing().when(issuedBooks).setBook((Book) any());
        doNothing().when(issuedBooks).setId((Long) any());
        doNothing().when(issuedBooks).setIssueEndDate((LocalDate) any());
        doNothing().when(issuedBooks).setIssueStartDate((LocalDate) any());
        doNothing().when(issuedBooks).setIssueStatus((IssueStatus) any());
        doNothing().when(issuedBooks).setUser((User) any());
        issuedBooks.setBook(book);
        issuedBooks.setId(123L);
        issuedBooks.setIssueEndDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStartDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStatus(IssueStatus.ACTIVE);
        issuedBooks.setUser(user);

        ArrayList<IssuedBooks> issuedBooksList = new ArrayList<>();
        issuedBooksList.add(issuedBooks);
        when(this.issuedBooksRepository.findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any()))
                .thenReturn(issuedBooksList);
        assertEquals(1, this.issuedBookService.findIssuedBooksByUserIdHistory(123L).size());
        verify(this.issuedBooksRepository).findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any());
        verify(issuedBooks).getIssueStatus();
        verify(issuedBooks).setBook((Book) any());
        verify(issuedBooks).setId((Long) any());
        verify(issuedBooks).setIssueEndDate((LocalDate) any());
        verify(issuedBooks).setIssueStartDate((LocalDate) any());
        verify(issuedBooks).setIssueStatus((IssueStatus) any());
        verify(issuedBooks).setUser((User) any());
    }

    /**
     * Method under test: {@link IssuedBookService#findIssuedBooksByUserIdHistory(Long)}
     */
    @Test
    void testFindIssuedBooksByUserIdHistory7() {
        when(this.issuedBooksRepository.findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any()))
                .thenReturn(new ArrayList<>());
        assertTrue(this.issuedBookService.findIssuedBooksByUserIdHistory(123L).isEmpty());
        verify(this.issuedBooksRepository).findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any());
    }

    /**
     * Method under test: {@link IssuedBookService#findIssuedBooksByUserIdHistory(Long)}
     */
    @Test
    void testFindIssuedBooksByUserIdHistory8() {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setId(123L);
        book.setIsbn("Isbn");
        book.setIssuedBooks(new ArrayList<>());
        book.setPages(1);
        book.setReservations(new ArrayList<>());
        book.setTitle("Dr");
        book.setYear(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setReservations(new ArrayList<>());
        user.setResetPasswordToken("ABC123");
        user.setUserRole(UserRole.ADMIN);

        IssuedBooks issuedBooks = new IssuedBooks();
        issuedBooks.setBook(book);
        issuedBooks.setId(123L);
        issuedBooks.setIssueEndDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStartDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStatus(IssueStatus.ACTIVE);
        issuedBooks.setUser(user);

        ArrayList<IssuedBooks> issuedBooksList = new ArrayList<>();
        issuedBooksList.add(issuedBooks);
        when(this.issuedBooksRepository.findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any()))
                .thenReturn(issuedBooksList);
        assertTrue(this.issuedBookService.findIssuedBooksByUserIdHistory(123L).isEmpty());
        verify(this.issuedBooksRepository).findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any());
    }

    /**
     * Method under test: {@link IssuedBookService#findIssuedBooksByUserIdHistory(Long)}
     */
    @Test
    void testFindIssuedBooksByUserIdHistory9() {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setId(123L);
        book.setIsbn("Isbn");
        book.setIssuedBooks(new ArrayList<>());
        book.setPages(1);
        book.setReservations(new ArrayList<>());
        book.setTitle("Dr");
        book.setYear(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setReservations(new ArrayList<>());
        user.setResetPasswordToken("ABC123");
        user.setUserRole(UserRole.ADMIN);
        IssuedBooks issuedBooks = mock(IssuedBooks.class);
        when(issuedBooks.getIssueStatus()).thenReturn(IssueStatus.HISTORY);
        doNothing().when(issuedBooks).setBook((Book) any());
        doNothing().when(issuedBooks).setId((Long) any());
        doNothing().when(issuedBooks).setIssueEndDate((LocalDate) any());
        doNothing().when(issuedBooks).setIssueStartDate((LocalDate) any());
        doNothing().when(issuedBooks).setIssueStatus((IssueStatus) any());
        doNothing().when(issuedBooks).setUser((User) any());
        issuedBooks.setBook(book);
        issuedBooks.setId(123L);
        issuedBooks.setIssueEndDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStartDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStatus(IssueStatus.ACTIVE);
        issuedBooks.setUser(user);

        ArrayList<IssuedBooks> issuedBooksList = new ArrayList<>();
        issuedBooksList.add(issuedBooks);
        when(this.issuedBooksRepository.findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any()))
                .thenReturn(issuedBooksList);
        assertEquals(1, this.issuedBookService.findIssuedBooksByUserIdHistory(123L).size());
        verify(this.issuedBooksRepository).findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any());
        verify(issuedBooks).getIssueStatus();
        verify(issuedBooks).setBook((Book) any());
        verify(issuedBooks).setId((Long) any());
        verify(issuedBooks).setIssueEndDate((LocalDate) any());
        verify(issuedBooks).setIssueStartDate((LocalDate) any());
        verify(issuedBooks).setIssueStatus((IssueStatus) any());
        verify(issuedBooks).setUser((User) any());
    }

    /**
     * Method under test: {@link IssuedBookService#findIssuedBooksByUserIdHistory(Long)}
     */
    @Test
    void testFindIssuedBooksByUserIdHistory10() {
        when(this.issuedBooksRepository.findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any()))
                .thenReturn(new ArrayList<>());
        assertTrue(this.issuedBookService.findIssuedBooksByUserIdHistory(123L).isEmpty());
        verify(this.issuedBooksRepository).findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any());
    }

    /**
     * Method under test: {@link IssuedBookService#findIssuedBooksByUserIdHistory(Long)}
     */
    @Test
    void testFindIssuedBooksByUserIdHistory11() {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setId(123L);
        book.setIsbn("Isbn");
        book.setIssuedBooks(new ArrayList<>());
        book.setPages(1);
        book.setReservations(new ArrayList<>());
        book.setTitle("Dr");
        book.setYear(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setReservations(new ArrayList<>());
        user.setResetPasswordToken("ABC123");
        user.setUserRole(UserRole.ADMIN);

        IssuedBooks issuedBooks = new IssuedBooks();
        issuedBooks.setBook(book);
        issuedBooks.setId(123L);
        issuedBooks.setIssueEndDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStartDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStatus(IssueStatus.ACTIVE);
        issuedBooks.setUser(user);

        ArrayList<IssuedBooks> issuedBooksList = new ArrayList<>();
        issuedBooksList.add(issuedBooks);
        when(this.issuedBooksRepository.findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any()))
                .thenReturn(issuedBooksList);
        assertTrue(this.issuedBookService.findIssuedBooksByUserIdHistory(123L).isEmpty());
        verify(this.issuedBooksRepository).findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any());
    }

    /**
     * Method under test: {@link IssuedBookService#findIssuedBooksByUserIdHistory(Long)}
     */
    @Test
    void testFindIssuedBooksByUserIdHistory12() {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setId(123L);
        book.setIsbn("Isbn");
        book.setIssuedBooks(new ArrayList<>());
        book.setPages(1);
        book.setReservations(new ArrayList<>());
        book.setTitle("Dr");
        book.setYear(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setReservations(new ArrayList<>());
        user.setResetPasswordToken("ABC123");
        user.setUserRole(UserRole.ADMIN);
        IssuedBooks issuedBooks = mock(IssuedBooks.class);
        when(issuedBooks.getIssueStatus()).thenReturn(IssueStatus.HISTORY);
        doNothing().when(issuedBooks).setBook((Book) any());
        doNothing().when(issuedBooks).setId((Long) any());
        doNothing().when(issuedBooks).setIssueEndDate((LocalDate) any());
        doNothing().when(issuedBooks).setIssueStartDate((LocalDate) any());
        doNothing().when(issuedBooks).setIssueStatus((IssueStatus) any());
        doNothing().when(issuedBooks).setUser((User) any());
        issuedBooks.setBook(book);
        issuedBooks.setId(123L);
        issuedBooks.setIssueEndDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStartDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStatus(IssueStatus.ACTIVE);
        issuedBooks.setUser(user);

        ArrayList<IssuedBooks> issuedBooksList = new ArrayList<>();
        issuedBooksList.add(issuedBooks);
        when(this.issuedBooksRepository.findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any()))
                .thenReturn(issuedBooksList);
        assertEquals(1, this.issuedBookService.findIssuedBooksByUserIdHistory(123L).size());
        verify(this.issuedBooksRepository).findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc((Long) any());
        verify(issuedBooks).getIssueStatus();
        verify(issuedBooks).setBook((Book) any());
        verify(issuedBooks).setId((Long) any());
        verify(issuedBooks).setIssueEndDate((LocalDate) any());
        verify(issuedBooks).setIssueStartDate((LocalDate) any());
        verify(issuedBooks).setIssueStatus((IssueStatus) any());
        verify(issuedBooks).setUser((User) any());
    }

    /**
     * Method under test: {@link IssuedBookService#issueBookWithActiveReservation(Book, User)}
     */
    @Test
    void testIssueBookWithActiveReservation() {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setId(123L);
        book.setIsbn("Isbn");
        book.setIssuedBooks(new ArrayList<>());
        book.setPages(1);
        book.setReservations(new ArrayList<>());
        book.setTitle("Dr");
        book.setYear(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setReservations(new ArrayList<>());
        user.setResetPasswordToken("ABC123");
        user.setUserRole(UserRole.ADMIN);

        IssuedBooks issuedBooks = new IssuedBooks();
        issuedBooks.setBook(book);
        issuedBooks.setId(123L);
        issuedBooks.setIssueEndDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStartDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStatus(IssueStatus.ACTIVE);
        issuedBooks.setUser(user);
        when(this.issuedBooksRepository.save((IssuedBooks) any())).thenReturn(issuedBooks);

        Book book1 = new Book();
        book1.setAuthor("JaneDoe");
        book1.setBookStatus(BookStatus.AVAILABLE);
        book1.setId(123L);
        book1.setIsbn("Isbn");
        book1.setIssuedBooks(new ArrayList<>());
        book1.setPages(1);
        book1.setReservations(new ArrayList<>());
        book1.setTitle("Dr");
        book1.setYear(1);
        when(this.bookDBService.updateBook((Book) any())).thenReturn(book1);

        Book book2 = new Book();
        book2.setAuthor("JaneDoe");
        book2.setBookStatus(BookStatus.AVAILABLE);
        book2.setId(123L);
        book2.setIsbn("Isbn");
        book2.setIssuedBooks(new ArrayList<>());
        book2.setPages(1);
        book2.setReservations(new ArrayList<>());
        book2.setTitle("Dr");
        book2.setYear(1);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setReservations(new ArrayList<>());
        user1.setResetPasswordToken("ABC123");
        user1.setUserRole(UserRole.ADMIN);
        this.issuedBookService.issueBookWithActiveReservation(book2, user1);
        verify(this.issuedBooksRepository).save((IssuedBooks) any());
        verify(this.bookDBService).updateBook((Book) any());
        assertEquals(BookStatus.ISSUED, book2.getBookStatus());
    }

    /**
     * Method under test: {@link IssuedBookService#findIssuedBooksByBookId(Long)}
     */
    @Test
    void testFindIssuedBooksByBookId() {
        ArrayList<IssuedBooks> issuedBooksList = new ArrayList<>();
        when(this.issuedBooksRepository.findIssuedBooksByBookIdEqualsOrderByIssueEndDateAsc((Long) any()))
                .thenReturn(issuedBooksList);
        List<IssuedBooks> actualFindIssuedBooksByBookIdResult = this.issuedBookService.findIssuedBooksByBookId(123L);
        assertSame(issuedBooksList, actualFindIssuedBooksByBookIdResult);
        assertTrue(actualFindIssuedBooksByBookIdResult.isEmpty());
        verify(this.issuedBooksRepository).findIssuedBooksByBookIdEqualsOrderByIssueEndDateAsc((Long) any());
    }

    /**
     * Method under test: {@link IssuedBookService#findIssueBooksByBookIdWithIssueStatusActive(Long)}
     */
    @Test
    void testFindIssueBooksByBookIdWithIssueStatusActive() {
        when(this.issuedBooksRepository.findIssuedBooksByBookIdEqualsOrderByIssueEndDateAsc((Long) any()))
                .thenReturn(new ArrayList<>());
        assertTrue(this.issuedBookService.findIssueBooksByBookIdWithIssueStatusActive(123L).isEmpty());
        verify(this.issuedBooksRepository).findIssuedBooksByBookIdEqualsOrderByIssueEndDateAsc((Long) any());
    }

    /**
     * Method under test: {@link IssuedBookService#findIssueBooksByBookIdWithIssueStatusActive(Long)}
     */
    @Test
    void testFindIssueBooksByBookIdWithIssueStatusActive2() {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setId(123L);
        book.setIsbn("Isbn");
        book.setIssuedBooks(new ArrayList<>());
        book.setPages(1);
        book.setReservations(new ArrayList<>());
        book.setTitle("Dr");
        book.setYear(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setReservations(new ArrayList<>());
        user.setResetPasswordToken("ABC123");
        user.setUserRole(UserRole.ADMIN);

        IssuedBooks issuedBooks = new IssuedBooks();
        issuedBooks.setBook(book);
        issuedBooks.setId(123L);
        issuedBooks.setIssueEndDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStartDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStatus(IssueStatus.ACTIVE);
        issuedBooks.setUser(user);

        ArrayList<IssuedBooks> issuedBooksList = new ArrayList<>();
        issuedBooksList.add(issuedBooks);
        when(this.issuedBooksRepository.findIssuedBooksByBookIdEqualsOrderByIssueEndDateAsc((Long) any()))
                .thenReturn(issuedBooksList);
        assertEquals(1, this.issuedBookService.findIssueBooksByBookIdWithIssueStatusActive(123L).size());
        verify(this.issuedBooksRepository).findIssuedBooksByBookIdEqualsOrderByIssueEndDateAsc((Long) any());
    }

    /**
     * Method under test: {@link IssuedBookService#findIssueBooksByBookIdWithIssueStatusActive(Long)}
     */
    @Test
    void testFindIssueBooksByBookIdWithIssueStatusActive3() {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setId(123L);
        book.setIsbn("Isbn");
        book.setIssuedBooks(new ArrayList<>());
        book.setPages(1);
        book.setReservations(new ArrayList<>());
        book.setTitle("Dr");
        book.setYear(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setReservations(new ArrayList<>());
        user.setResetPasswordToken("ABC123");
        user.setUserRole(UserRole.ADMIN);
        IssuedBooks issuedBooks = mock(IssuedBooks.class);
        when(issuedBooks.getIssueStatus()).thenReturn(IssueStatus.HISTORY);
        doNothing().when(issuedBooks).setBook((Book) any());
        doNothing().when(issuedBooks).setId((Long) any());
        doNothing().when(issuedBooks).setIssueEndDate((LocalDate) any());
        doNothing().when(issuedBooks).setIssueStartDate((LocalDate) any());
        doNothing().when(issuedBooks).setIssueStatus((IssueStatus) any());
        doNothing().when(issuedBooks).setUser((User) any());
        issuedBooks.setBook(book);
        issuedBooks.setId(123L);
        issuedBooks.setIssueEndDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStartDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStatus(IssueStatus.ACTIVE);
        issuedBooks.setUser(user);

        ArrayList<IssuedBooks> issuedBooksList = new ArrayList<>();
        issuedBooksList.add(issuedBooks);
        when(this.issuedBooksRepository.findIssuedBooksByBookIdEqualsOrderByIssueEndDateAsc((Long) any()))
                .thenReturn(issuedBooksList);
        assertTrue(this.issuedBookService.findIssueBooksByBookIdWithIssueStatusActive(123L).isEmpty());
        verify(this.issuedBooksRepository).findIssuedBooksByBookIdEqualsOrderByIssueEndDateAsc((Long) any());
        verify(issuedBooks).getIssueStatus();
        verify(issuedBooks).setBook((Book) any());
        verify(issuedBooks).setId((Long) any());
        verify(issuedBooks).setIssueEndDate((LocalDate) any());
        verify(issuedBooks).setIssueStartDate((LocalDate) any());
        verify(issuedBooks).setIssueStatus((IssueStatus) any());
        verify(issuedBooks).setUser((User) any());
    }

    /**
     * Method under test: {@link IssuedBookService#findIssueBooksByBookIdWithIssueStatusActive(Long)}
     */
    @Test
    void testFindIssueBooksByBookIdWithIssueStatusActive4() {
        when(this.issuedBooksRepository.findIssuedBooksByBookIdEqualsOrderByIssueEndDateAsc((Long) any()))
                .thenReturn(new ArrayList<>());
        assertTrue(this.issuedBookService.findIssueBooksByBookIdWithIssueStatusActive(123L).isEmpty());
        verify(this.issuedBooksRepository).findIssuedBooksByBookIdEqualsOrderByIssueEndDateAsc((Long) any());
    }

    /**
     * Method under test: {@link IssuedBookService#findIssueBooksByBookIdWithIssueStatusActive(Long)}
     */
    @Test
    void testFindIssueBooksByBookIdWithIssueStatusActive5() {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setId(123L);
        book.setIsbn("Isbn");
        book.setIssuedBooks(new ArrayList<>());
        book.setPages(1);
        book.setReservations(new ArrayList<>());
        book.setTitle("Dr");
        book.setYear(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setReservations(new ArrayList<>());
        user.setResetPasswordToken("ABC123");
        user.setUserRole(UserRole.ADMIN);

        IssuedBooks issuedBooks = new IssuedBooks();
        issuedBooks.setBook(book);
        issuedBooks.setId(123L);
        issuedBooks.setIssueEndDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStartDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStatus(IssueStatus.ACTIVE);
        issuedBooks.setUser(user);

        ArrayList<IssuedBooks> issuedBooksList = new ArrayList<>();
        issuedBooksList.add(issuedBooks);
        when(this.issuedBooksRepository.findIssuedBooksByBookIdEqualsOrderByIssueEndDateAsc((Long) any()))
                .thenReturn(issuedBooksList);
        assertEquals(1, this.issuedBookService.findIssueBooksByBookIdWithIssueStatusActive(123L).size());
        verify(this.issuedBooksRepository).findIssuedBooksByBookIdEqualsOrderByIssueEndDateAsc((Long) any());
    }

    /**
     * Method under test: {@link IssuedBookService#findIssueBooksByBookIdWithIssueStatusActive(Long)}
     */
    @Test
    void testFindIssueBooksByBookIdWithIssueStatusActive6() {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setId(123L);
        book.setIsbn("Isbn");
        book.setIssuedBooks(new ArrayList<>());
        book.setPages(1);
        book.setReservations(new ArrayList<>());
        book.setTitle("Dr");
        book.setYear(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setReservations(new ArrayList<>());
        user.setResetPasswordToken("ABC123");
        user.setUserRole(UserRole.ADMIN);
        IssuedBooks issuedBooks = mock(IssuedBooks.class);
        when(issuedBooks.getIssueStatus()).thenReturn(IssueStatus.HISTORY);
        doNothing().when(issuedBooks).setBook((Book) any());
        doNothing().when(issuedBooks).setId((Long) any());
        doNothing().when(issuedBooks).setIssueEndDate((LocalDate) any());
        doNothing().when(issuedBooks).setIssueStartDate((LocalDate) any());
        doNothing().when(issuedBooks).setIssueStatus((IssueStatus) any());
        doNothing().when(issuedBooks).setUser((User) any());
        issuedBooks.setBook(book);
        issuedBooks.setId(123L);
        issuedBooks.setIssueEndDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStartDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStatus(IssueStatus.ACTIVE);
        issuedBooks.setUser(user);

        ArrayList<IssuedBooks> issuedBooksList = new ArrayList<>();
        issuedBooksList.add(issuedBooks);
        when(this.issuedBooksRepository.findIssuedBooksByBookIdEqualsOrderByIssueEndDateAsc((Long) any()))
                .thenReturn(issuedBooksList);
        assertTrue(this.issuedBookService.findIssueBooksByBookIdWithIssueStatusActive(123L).isEmpty());
        verify(this.issuedBooksRepository).findIssuedBooksByBookIdEqualsOrderByIssueEndDateAsc((Long) any());
        verify(issuedBooks).getIssueStatus();
        verify(issuedBooks).setBook((Book) any());
        verify(issuedBooks).setId((Long) any());
        verify(issuedBooks).setIssueEndDate((LocalDate) any());
        verify(issuedBooks).setIssueStartDate((LocalDate) any());
        verify(issuedBooks).setIssueStatus((IssueStatus) any());
        verify(issuedBooks).setUser((User) any());
    }

    /**
     * Method under test: {@link IssuedBookService#returnBook(Long, LocalDate)}
     */
    @Test
    void testReturnBook() {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setId(123L);
        book.setIsbn("Isbn");
        book.setIssuedBooks(new ArrayList<>());
        book.setPages(1);
        book.setReservations(new ArrayList<>());
        book.setTitle("Dr");
        book.setYear(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setReservations(new ArrayList<>());
        user.setResetPasswordToken("ABC123");
        user.setUserRole(UserRole.ADMIN);

        IssuedBooks issuedBooks = new IssuedBooks();
        issuedBooks.setBook(book);
        issuedBooks.setId(123L);
        issuedBooks.setIssueEndDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStartDate(LocalDate.ofEpochDay(1L));
        issuedBooks.setIssueStatus(IssueStatus.ACTIVE);
        issuedBooks.setUser(user);

        Book book1 = new Book();
        book1.setAuthor("JaneDoe");
        book1.setBookStatus(BookStatus.AVAILABLE);
        book1.setId(123L);
        book1.setIsbn("Isbn");
        book1.setIssuedBooks(new ArrayList<>());
        book1.setPages(1);
        book1.setReservations(new ArrayList<>());
        book1.setTitle("Dr");
        book1.setYear(1);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setReservations(new ArrayList<>());
        user1.setResetPasswordToken("ABC123");
        user1.setUserRole(UserRole.ADMIN);

        IssuedBooks issuedBooks1 = new IssuedBooks();
        issuedBooks1.setBook(book1);
        issuedBooks1.setId(123L);
        issuedBooks1.setIssueEndDate(LocalDate.ofEpochDay(1L));
        issuedBooks1.setIssueStartDate(LocalDate.ofEpochDay(1L));
        issuedBooks1.setIssueStatus(IssueStatus.ACTIVE);
        issuedBooks1.setUser(user1);
        when(this.issuedBooksRepository.save((IssuedBooks) any())).thenReturn(issuedBooks1);
        when(this.issuedBooksRepository.getById((Long) any())).thenReturn(issuedBooks);

        Book book2 = new Book();
        book2.setAuthor("JaneDoe");
        book2.setBookStatus(BookStatus.AVAILABLE);
        book2.setId(123L);
        book2.setIsbn("Isbn");
        book2.setIssuedBooks(new ArrayList<>());
        book2.setPages(1);
        book2.setReservations(new ArrayList<>());
        book2.setTitle("Dr");
        book2.setYear(1);
        when(this.bookDBService.updateBook((Book) any())).thenReturn(book2);
        this.issuedBookService.returnBook(123L, LocalDate.ofEpochDay(1L));
        verify(this.issuedBooksRepository).getById((Long) any());
        verify(this.issuedBooksRepository).save((IssuedBooks) any());
        verify(this.bookDBService).updateBook((Book) any());
    }
}

