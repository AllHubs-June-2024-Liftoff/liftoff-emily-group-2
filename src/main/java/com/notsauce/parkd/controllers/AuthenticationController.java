package com.notsauce.parkd.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import com.notsauce.parkd.models.User;

import com.notsauce.parkd.models.data.UserRepository;
import com.notsauce.parkd.models.dto.LoginFormDTO;
import com.notsauce.parkd.models.dto.RegistrationFormDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Controller
public class AuthenticationController {


    @Autowired
    private UserRepository userRepository;

    

    private static final String userSessionKey = "user";

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    public User getUserFromSession(HttpSession session) {

        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            return null;
        }
        return userOptional.get();
    }

    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute(new RegistrationFormDTO()); // automatically creates variable registrationFormDTO
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegistrationFormDTO registrationFormDTO,
                                          Errors errors,
                                          HttpServletRequest request,
                                          Model model) {

        if (errors.hasErrors()) {
            return "register";
        }
        User existingUser = userRepository.findByUsername(registrationFormDTO.getUsername());

        if (existingUser != null) {
            errors.rejectValue(
                    "username",
                    "username.alreadyexists",
                    "A user with that username already exists"
            );
            return "register";
        }

        String password = registrationFormDTO.getPassword();
        String verifyPassword = registrationFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue(
                    "password",
                    "passwords.mismatch",
                    "Passwords do not match");
            return "register";
        }

        User newUser = new User(registrationFormDTO.getUsername(), registrationFormDTO.getPassword());
        userRepository.save(newUser);
        //setUserInSession(request.getSession(), newUser);
       //return "redirect:/";



        setUserInSession(request.getSession(), newUser);
        return "redirect:/";


    }

    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        model.addAttribute(new LoginFormDTO());
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   Errors errors,
                                   HttpServletRequest request) {

        if (errors.hasErrors()) {
            return "login";
        }

        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());

        String password = loginFormDTO.getPassword();

        if (theUser == null || !theUser.isMatchingPassword(password)) {
            errors.rejectValue(
                    "password",
                    "login.invalid",
                    "***Invalid Login Credentials***"
            );
            return "login";
        }

        setUserInSession(request.getSession(), theUser);
        return "redirect:/";
    }



    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(HttpServletRequest request,
                         Model model){
       HttpSession session = request.getSession();
       User user = getUserFromSession(session);
       model.addAttribute("user",user.getUsername());
       return "profile";
    }

    @GetMapping("/profile/{username}") // Profile of another user
    public String userProfile(@PathVariable String username, Model model) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return "userNotFound"; // Important: Handle the case where the user isn't found
        }
        model.addAttribute("user", user.getUsername());
        return "profile";
    }



}