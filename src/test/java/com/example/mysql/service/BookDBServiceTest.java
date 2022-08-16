package com.example.mysql.service;

import com.example.mysql.model.Book;
import com.example.mysql.model.BookSearch;
import com.example.mysql.model.BookStatus;
import com.example.mysql.repository.BookRepository;
import com.example.mysql.repository.ReservationRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {BookDBService.class})
@ExtendWith(SpringExtension.class)
class BookDBServiceTest {
    @Autowired
    private BookDBService bookDBService;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private ReservationRepository reservationRepository;

    /**
     * Method under test: {@link BookDBService#getAllBooks()}
     */
    @Test
    void testGetAllBooks() {
        ArrayList<Book> bookList = new ArrayList<>();
        when(this.bookRepository.findAll()).thenReturn(bookList);
        List<Book> actualAllBooks = this.bookDBService.getAllBooks();
        assertSame(bookList, actualAllBooks);
        assertTrue(actualAllBooks.isEmpty());
        verify(this.bookRepository).findAll();
    }

    /**
     * Method under test: {@link BookDBService#getAllBooks()}
     */
    @Test
    void testGetAllBooks2() {
        when(this.bookRepository.findAll()).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> this.bookDBService.getAllBooks());
        verify(this.bookRepository).findAll();
    }

    /**
     * Method under test: {@link BookDBService#getAllAvailableBooks()}
     */
    @Test
    void testGetAllAvailableBooks() {
        when(this.bookRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(this.bookDBService.getAllAvailableBooks().isEmpty());
        verify(this.bookRepository).findAll();
    }

    /**
     * Method under test: {@link BookDBService#getAllAvailableBooks()}
     */
    @Test
    void testGetAllAvailableBooks2() {
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

        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book);
        when(this.bookRepository.findAll()).thenReturn(bookList);
        assertEquals(1, this.bookDBService.getAllAvailableBooks().size());
        verify(this.bookRepository).findAll();
    }

    /**
     * Method under test: {@link BookDBService#getAllAvailableBooks()}
     */
    @Test
    void testGetAllAvailableBooks3() {
        when(this.bookRepository.findAll()).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> this.bookDBService.getAllAvailableBooks());
        verify(this.bookRepository).findAll();
    }

    /**
     * Method under test: {@link BookDBService#getAllAvailableBooks()}
     */
    @Test
    void testGetAllAvailableBooks4() {
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

        Book book1 = new Book();
        book1.setAuthor("JaneDoe");
        book1.setBookStatus(BookStatus.AVAILABLE);
        book1.setId(123L);
        book1.setIsbn("Isbn");
        book1.setIssuedBooks(new ArrayList<>());
        book1.setPages(5);
        book1.setReservations(new ArrayList<>());
        book1.setTitle("Dr");
        book1.setYear(5);

        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book);
        when(this.bookRepository.findAll()).thenReturn(bookList);
        assertEquals(2, this.bookDBService.getAllAvailableBooks().size());
        verify(this.bookRepository).findAll();
    }

    /**
     * Method under test: {@link BookDBService#getAllAvailableBooks()}
     */
    @Test
    void testGetAllAvailableBooks5() {
        when(this.bookRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(this.bookDBService.getAllAvailableBooks().isEmpty());
        verify(this.bookRepository).findAll();
    }

    /**
     * Method under test: {@link BookDBService#getAllAvailableBooks()}
     */
    @Test
    void testGetAllAvailableBooks6() {
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

        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book);
        when(this.bookRepository.findAll()).thenReturn(bookList);
        assertEquals(1, this.bookDBService.getAllAvailableBooks().size());
        verify(this.bookRepository).findAll();
    }

    /**
     * Method under test: {@link BookDBService#getAllAvailableBooks()}
     */
    @Test
    void testGetAllAvailableBooks7() {
        when(this.bookRepository.findAll()).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> this.bookDBService.getAllAvailableBooks());
        verify(this.bookRepository).findAll();
    }

    /**
     * Method under test: {@link BookDBService#getAllAvailableBooks()}
     */
    @Test
    void testGetAllAvailableBooks8() {
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

        Book book1 = new Book();
        book1.setAuthor("JaneDoe");
        book1.setBookStatus(BookStatus.AVAILABLE);
        book1.setId(123L);
        book1.setIsbn("Isbn");
        book1.setIssuedBooks(new ArrayList<>());
        book1.setPages(5);
        book1.setReservations(new ArrayList<>());
        book1.setTitle("Dr");
        book1.setYear(5);

        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book);
        when(this.bookRepository.findAll()).thenReturn(bookList);
        assertEquals(2, this.bookDBService.getAllAvailableBooks().size());
        verify(this.bookRepository).findAll();
    }

    /**
     * Method under test: {@link BookDBService#saveBook(Book)}
     */
    @Test
    void testSaveBook() {
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
        when(this.bookRepository.save((Book) any())).thenReturn(book);

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
        assertSame(book, this.bookDBService.saveBook(book1));
        verify(this.bookRepository).save((Book) any());
        assertEquals(BookStatus.AVAILABLE, book1.getBookStatus());
    }

    /**
     * Method under test: {@link BookDBService#saveBook(Book)}
     */
    @Test
    void testSaveBook2() {
        when(this.bookRepository.save((Book) any())).thenThrow(new RuntimeException("An error occurred"));

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
        assertThrows(RuntimeException.class, () -> this.bookDBService.saveBook(book));
        verify(this.bookRepository).save((Book) any());
    }

    /**
     * Method under test: {@link BookDBService#getBookById(Long)}
     */
    @Test
    void testGetBookById() {
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
        Optional<Book> ofResult = Optional.of(book);
        when(this.bookRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(book, this.bookDBService.getBookById(123L));
        verify(this.bookRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link BookDBService#getBookById(Long)}
     */
    @Test
    void testGetBookById2() {
        when(this.bookRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> this.bookDBService.getBookById(123L));
        verify(this.bookRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link BookDBService#getBookById(Long)}
     */
    @Test
    void testGetBookById3() {
        when(this.bookRepository.findById((Long) any())).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> this.bookDBService.getBookById(123L));
        verify(this.bookRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link BookDBService#updateBook(Book)}
     */
    @Test
    void testUpdateBook() {
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
        when(this.bookRepository.save((Book) any())).thenReturn(book);

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
        assertSame(book, this.bookDBService.updateBook(book1));
        verify(this.bookRepository).save((Book) any());
    }

    /**
     * Method under test: {@link BookDBService#updateBook(Book)}
     */
    @Test
    void testUpdateBook2() {
        when(this.bookRepository.save((Book) any())).thenThrow(new RuntimeException("An error occurred"));

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
        assertThrows(RuntimeException.class, () -> this.bookDBService.updateBook(book));
        verify(this.bookRepository).save((Book) any());
    }

    /**
     * Method under test: {@link BookDBService#deleteBookById(Long)}
     */
    @Test
    void testDeleteBookById() {
        doNothing().when(this.bookRepository).deleteById((Long) any());
        this.bookDBService.deleteBookById(123L);
        verify(this.bookRepository).deleteById((Long) any());
        assertTrue(this.bookDBService.findRandom().isEmpty());
    }

    /**
     * Method under test: {@link BookDBService#deleteBookById(Long)}
     */
    @Test
    void testDeleteBookById2() {
        doThrow(new RuntimeException("An error occurred")).when(this.bookRepository).deleteById((Long) any());
        assertThrows(RuntimeException.class, () -> this.bookDBService.deleteBookById(123L));
        verify(this.bookRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link BookDBService#search(BookSearch)}
     */
    @Test
    void testSearch() {
        ArrayList<Book> bookList = new ArrayList<>();
        when(this.bookRepository.findAll((org.springframework.data.domain.Example<Book>) any())).thenReturn(bookList);
        List<Book> actualSearchResult = this.bookDBService.search(new BookSearch());
        assertSame(bookList, actualSearchResult);
        assertTrue(actualSearchResult.isEmpty());
        verify(this.bookRepository).findAll((org.springframework.data.domain.Example<Book>) any());
    }

    /**
     * Method under test: {@link BookDBService#search(BookSearch)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSearch2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.mysql.service.BookDBService.search(BookDBService.java:64)
        //   In order to prevent search(BookSearch)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   search(BookSearch).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.bookRepository.findAll((org.springframework.data.domain.Example<Book>) any()))
                .thenReturn(new ArrayList<>());
        this.bookDBService.search(null);
    }

    /**
     * Method under test: {@link BookDBService#search(BookSearch)}
     */
    @Test
    void testSearch3() {
        when(this.bookRepository.findAll((org.springframework.data.domain.Example<Book>) any()))
                .thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> this.bookDBService.search(new BookSearch()));
        verify(this.bookRepository).findAll((org.springframework.data.domain.Example<Book>) any());
    }

    /**
     * Method under test: {@link BookDBService#search(BookSearch)}
     */
    @Test
    void testSearch4() {
        ArrayList<Book> bookList = new ArrayList<>();
        when(this.bookRepository.findAll((org.springframework.data.domain.Example<Book>) any())).thenReturn(bookList);
        List<Book> actualSearchResult = this.bookDBService.search(new BookSearch());
        assertSame(bookList, actualSearchResult);
        assertTrue(actualSearchResult.isEmpty());
        verify(this.bookRepository).findAll((org.springframework.data.domain.Example<Book>) any());
    }

    /**
     * Method under test: {@link BookDBService#search(BookSearch)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSearch5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.mysql.service.BookDBService.search(BookDBService.java:64)
        //   In order to prevent search(BookSearch)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   search(BookSearch).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.bookRepository.findAll((org.springframework.data.domain.Example<Book>) any()))
                .thenReturn(new ArrayList<>());
        this.bookDBService.search(null);
    }

    /**
     * Method under test: {@link BookDBService#search(BookSearch)}
     */
    @Test
    void testSearch6() {
        when(this.bookRepository.findAll((org.springframework.data.domain.Example<Book>) any()))
                .thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> this.bookDBService.search(new BookSearch()));
        verify(this.bookRepository).findAll((org.springframework.data.domain.Example<Book>) any());
    }
}

