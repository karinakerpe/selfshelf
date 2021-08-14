package com.example.mysql.controller;

import com.example.mysql.security.CurrentUser;
import com.example.mysql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/users/admin")
public class AdminController {
    private UserService service;
    private CurrentUser currentUser;






}
