package com.example.mysql;


import com.example.mysql.exception.UserNotFoundException;
import com.example.mysql.model.user.User;
import com.example.mysql.repository.UserRepository;
import com.example.mysql.service.UserService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
@RequiredArgsConstructor
@Controller
public class ForgotPasswordController {

    @Autowired
    private UserService userService;

@Autowired
    private JavaMailSender mailSender;
@Autowired
private UserRepository userRepository;
    @GetMapping("/forgot_password")
    public String showForgotPasswordForm(Model model){
        model.addAttribute("pageTitle", "Forgot Password");

        return "forgot_password_form";
    }
    @PostMapping("/forgot_password")
    public String processForgotPasswordForm(HttpServletRequest request,
                                            Model model){
        String email = request.getParameter("email");
       String token =  RandomString.make(30);


        try {
           userService.updateResetPasswordToken(token, email);

            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token"  + token;

          sendEmail(email, resetPasswordLink);


        } catch (UserNotFoundException ex) {
          model.addAttribute("error", ex.getMessage());
      } catch
        (UnsupportedEncodingException | MessagingException e) {
           model.addAttribute("error", "Error while sending email.");
        }

        return "forgot_password_form";
    }

    private void sendEmail(String email, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("bob.in.wonderland.library@gmail.com", "Library Support");
        helper.setTo(email);

        String subject = "Here's the link to reset your password";

        String content = "<p> Hello, </p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password: </p>"
                + "<p><b><a href=\"" + resetPasswordLink + "\"> Change my password </a><b></p>"
                + "<p>Ignore this email if you do remember your password, or you have not made the request </p>";
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);




    }
}
