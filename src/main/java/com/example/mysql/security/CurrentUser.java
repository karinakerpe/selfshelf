package com.example.mysql.security;

import com.example.mysql.model.user.User;
import com.example.mysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CurrentUser {
    @Autowired
    UserService userService;
    public long getCurrentUserId() {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = details.getUsername();
        long userId = -1;
        for (User user : userService.listAll()) {
            if (user.getEmail().equals(username)) {
                userId = user.getId();
                break;
            }
        }
        return userId;
    }

    public User getCurrentUser() {
        User currentUser = userService.getById(getCurrentUserId());
        return currentUser;
    }

}
