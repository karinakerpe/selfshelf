package com.example.mysql.controller;

import com.example.mysql.model.user.User;
import com.example.mysql.security.CurrentUser;
import com.example.mysql.service.BookRecordService;
import com.example.mysql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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
        model.addAttribute("books", bookRecordService.getAllBooks());
        return "index";
    }


    @GetMapping("/account")
    public String showAccountPage() {
        return "account";
    }




    @GetMapping("/users")
    public String viewPageUsers(Model model) {

        Long currentUserId = currentUser.getCurrentUserId();
        User currentUser = service.getById(currentUserId);
        model.addAttribute("listUsers", service.listAll());
        model.addAttribute("firstName", currentUser.getFullName());
        model.addAttribute("permissions", currentUser.getUserRole().getGrantedAuthorities());
        return "users";
    }


    @PostMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "users_form";
        }

        service.update(id, user);

        return "redirect:/users";
    }


    @PostMapping("/save")
    public String saveUser(User user) {
        service.save(user);

        return "redirect:/list_users";

    }

}
