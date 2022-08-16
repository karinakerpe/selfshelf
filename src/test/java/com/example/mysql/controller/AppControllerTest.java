package com.example.mysql.controller;

import static org.mockito.Mockito.when;

import com.example.mysql.model.Book;
import com.example.mysql.model.BookStatus;
import com.example.mysql.security.CurrentUser;
import com.example.mysql.service.BookRecordService;
import com.example.mysql.service.UserService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AppController.class})
@ExtendWith(SpringExtension.class)
class AppControllerTest {
    @Autowired
    private AppController appController;

    @MockBean
    private BookRecordService bookRecordService;

    @MockBean
    private CurrentUser currentUser;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link AppController#viewHomePage(org.springframework.ui.Model)}
     */
    @Test
    void testViewHomePage() throws Exception {
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
        when(this.bookRecordService.findRandom()).thenReturn(bookList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        MockMvcBuilders.standaloneSetup(this.appController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("randomIsbn"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("index"));
    }

    /**
     * Method under test: {@link AppController#viewHomePage(org.springframework.ui.Model)}
     */
    @Test
    void testViewHomePage2() throws Exception {
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
        when(this.bookRecordService.findRandom()).thenReturn(bookList);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.appController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("randomIsbn"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("index"));
    }
}

