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


    @GetMapping("/user-account")
    public String showUserAccountPage() {
        return "user-account";
    }

//    @GetMapping("/admin-account")
//    public String showAdminAccountPage(Model model) {
//        Long currentUserId = currentUser.getCurrentUserId();
//        User currentUser = service.getById(currentUserId);
//        model.addAttribute("fullName", currentUser.getFullName());
//        return "admin-account";
//    }


    @GetMapping("/users")
    public String viewPageUsers(Model model) {

        Long currentUserId = currentUser.getCurrentUserId();
        User currentUser = service.getById(currentUserId);
        model.addAttribute("listUsers", service.listAll());
        model.addAttribute("firstName", currentUser.getFullName());
        model.addAttribute("permissions", currentUser.getUserRole().getGrantedAuthorities());
        return "users";
    }


    @GetMapping("/users/update/{id}")
    public String editById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("pageName", "Edit New User");

        User user = service.getById(id);
        model.addAttribute("user", user);

        return "user_form";
    }

    @PostMapping("/users/update/{id}")
    public String editUser(@PathVariable("id") Long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user_form";
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
