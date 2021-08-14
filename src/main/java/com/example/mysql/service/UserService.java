package com.example.mysql.service;

import com.example.mysql.exception.UserNotFoundException;
import com.example.mysql.model.user.User;
import com.example.mysql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mysql.model.user.UserRole.*;

@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepo;

    public User findUserByEmail (String email){
        return userRepo.findByEmail(email);
    }


    public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepo.save(user);
        } else {
            throw new UserNotFoundException("Could not find any customer with the email " + email);
        }
    }

    public User getByResetPasswordToken(String token) {
        return userRepo.findByResetPasswordToken(token);
    }

    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        user.setResetPasswordToken(null);
        userRepo.save(user);
    }


    public void saveUserWithDefaultRole(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        //setting default Role - USER
        user.setUserRole(USER);

        userRepo.save(user);

    }
    public void saveUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);

    }



    public void save(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public List<User> listAll() {
        return userRepo.findAll();
    }

    public User getById (Long id) {
        return userRepo.findById(id).get();
    }




    public User update(Long id, User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setEmail(user.getEmail());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        existingUser.setPassword(encodedPassword);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setUserRole(user.getUserRole());

        return userRepo.save(existingUser);
    }



    public String getFullName (User user){
        return user.getFirstName()+" "+user.getLastName();
    }
}
