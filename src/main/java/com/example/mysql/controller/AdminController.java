package com.example.mysql.controller;

import com.example.mysql.security.CurrentUser;
import com.example.mysql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
@RequestMapping("/users/admin")
public class AdminController {
    private UserService service;
    private CurrentUser currentUser;




    @GetMapping("/account")
    public String showAdminAccountPage(Model model, Principal principal) {
//        User user = service.getById(id);
//        model.addAttribute("fullName", user.getFullName());
//        model.addAttribute("adminId", user.getId());

//        principal.getName()
//                //find by email
//        model.addAttribute("fullName", currentUser.getFullName());
//        model.addAttribute("adminId", currentUser.getId());

        return "admin_account";
    }

}
