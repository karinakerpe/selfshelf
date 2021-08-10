package com.example.mysql.controller;

import com.example.mysql.model.user.User;
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


    @GetMapping
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user) {
        service.saveUserWithDefaultRole(user);

        return "registration_successful";

    }

    @GetMapping("/users")
    public String viewPageUsers(Model model) {
        model.addAttribute("listUsers", service.listAll());

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
