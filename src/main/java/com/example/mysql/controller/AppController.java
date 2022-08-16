package com.example.mysql.controller;

import com.example.mysql.model.Book;
import com.example.mysql.security.CurrentUser;
import com.example.mysql.service.BookRecordService;
import com.example.mysql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class AppController {
    @Autowired
    private UserService service;

    @Autowired
    private BookRecordService bookRecordService;

    @Autowired
    private CurrentUser currentUser;

    @GetMapping
    public String viewHomePage(Model model) {
        List<Book> listRandom = bookRecordService.findRandom();
        String randomIsbn = listRandom.get(0).getIsbn();
        model.addAttribute("randomIsbn", randomIsbn);
        return "index";
    }
}
