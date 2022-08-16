package com.example.mysql.controller;

import com.example.mysql.model.IssuedBooks;
import com.example.mysql.model.user.User;
import com.example.mysql.security.CurrentUser;
import com.example.mysql.service.BookRecordService;
import com.example.mysql.service.IssuedBookService;
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
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private BookRecordService bookRecordService;

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private IssuedBookService issuedBookService;

    @GetMapping("/user-reserve")
    public String showUserAccountPage() {
        return "user_reserve";
    }

    //page after login
    @GetMapping("/account")
    public String showAdminAccountPage(Model model, Principal principal) {
        String currentUserEmail = principal.getName();
        User currentUser = service.findUserByEmail(currentUserEmail);
        model.addAttribute("fullName", currentUser.getFullName());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("id", currentUser.getId());
        List<IssuedBooks> booksList = issuedBookService.getIssuedBooksWithStatusActive();
        model.addAttribute("bookList",booksList);
        return "user_account";
    }

    @GetMapping("/users")
    public String viewPageUsers(Model model, Principal principal) {
        String currentUserEmail = principal.getName();
        User currentUser = service.findUserByEmail(currentUserEmail);
        Long currentUserId = currentUser.getId();
        model.addAttribute("id", currentUserId);
        model.addAttribute("listUsers", service.listAll());
        model.addAttribute("firstName", currentUser.getFullName());
        model.addAttribute("permissions", currentUser.getUserRole().getGrantedAuthorities());
        return "users";
    }

    @GetMapping("/users/update/{id}")
    public String editUserById(@PathVariable("id") Long id, Model model, Principal principal) {
        String currentUserEmail = principal.getName();
        User currentUser = service.findUserByEmail(currentUserEmail);
        model.addAttribute("pageName", "Edit " + currentUser.getFullName() + " info:");
        User user = service.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("currentUser", currentUser);
        return "user_form";
    }

    @PostMapping("/users/update/{id}")
    public String editUser(@PathVariable("id") Long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user_form";
        }
        service.update(id, user);

        try {
            if (service.getById(id).getUserRole().name().isEmpty()) {
                return "redirect:/account";
            } else {
                return "redirect:/users";
            }
        } catch (NullPointerException e) {
            return "redirect:/account";
        }

    }


    @PostMapping("/save")
    public String saveUser(User user) {
        service.save(user);
        return "redirect:/list_users";

    }
}
