package com.example.mysql.service;

import com.example.mysql.model.Book;
import com.example.mysql.model.BookSearch;
import com.example.mysql.repository.BookRepository;
import com.example.mysql.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.mysql.model.BookStatus.AVAILABLE;
import static org.springframework.data.domain.ExampleMatcher.matchingAll;

@RequiredArgsConstructor
@Service
public class BookDBService implements BookRecordService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private BookRepository bookRepository;

//    public BookDBService(BookRepository bookRepository) {
//        super();
//        this.bookRepository = bookRepository;
//    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getAllAvailableBooks() {
        List<Book> allAvailableBooks = new ArrayList<>();
        List<Book> allBooks = bookRepository.findAll();
        for (Book allBook : allBooks) {
            if (allBook.getBookStatus().equals(AVAILABLE)) {
                allAvailableBooks.add(allBook);
            }
        }

        return allAvailableBooks;
    }

//    public List<Book> getReservedBooksForUserId(Long UserId) {
//        List<Long> booksIdListReservedByUserId = reservationRepository.FindReservedBooksIdByUserId(UserId);
//        List<Book> booksByBookId = new ArrayList<>();
//        List<Book> allBooks = bookRepository.findAll();
//        for (Long userId : booksIdListReservedByUserId) {
//            for (Book allBook : allBooks) {
//                if (allBook.getId().equals(userId)) {
//                    booksByBookId.add(allBook);
//                }
//            }
//        }
//
//        return booksByBookId;
//    }


    @Override
    public Book saveBook(Book book) {
        book.setBookStatus(AVAILABLE);
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> search(BookSearch bookSearch) {
        Book book = new Book();
        book.setTitle(bookSearch.getTitle());
        book.setAuthor(bookSearch.getAuthor());
        book.setYear(bookSearch.getYear());
        book.setPages(bookSearch.getPages());

        Example<Book> bookExample = Example.of(book, matchingAll().withIgnoreNullValues());
        return bookRepository.findAll(bookExample);
}

    public List<Book> findRandom(){
        return bookRepository.findRandomBook();
    }

}

