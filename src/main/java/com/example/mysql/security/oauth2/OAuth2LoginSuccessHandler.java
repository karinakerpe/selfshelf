package com.example.mysql.security.oauth2;

import com.example.mysql.model.user.User;
import com.example.mysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
@Autowired private UserService userService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        UserOAuth2User oauth2User = (UserOAuth2User) authentication.getPrincipal();
        String email = oauth2User.getEmail();

        System.out.println("OAuth2 username: " + oauth2User.getName());
        System.out.println("OAuth2 email: " + email);

        User user =  userService.findUserByEmail(email);

        if (user != null){
            System.out.println("User account already exists in database");
        } else {
            System.out.println("New user. About to add a new entry  in database...");
        }
        super.onAuthenticationSuccess(request, response, authentication);


    }
}
