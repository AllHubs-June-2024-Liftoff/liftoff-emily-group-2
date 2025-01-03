package com.notsauce.parkd.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import com.notsauce.parkd.models.User;
import com.notsauce.parkd.models.data.UserRepository;
import com.notsauce.parkd.models.dto.LoginFormDTO;
import com.notsauce.parkd.models.dto.RegistrationFormDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    private static final String userSessionKey = "user";

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }
    // Look up user with key
    public User getUserFromSession(HttpSession session) {

        // Get user ID from database using key
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }
        // Get optional back from database
        Optional<User> userOptional = userRepository.findById(userId);

        // Early return with null if user not found
        if (userOptional.isEmpty()) {
            return null;
        }
        // Return user object (unboxed from optional)
        return userOptional.get();
    }

    // Handlers for registration form
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

        // Send user back to form if errors are found
        if (errors.hasErrors()) {
            return "register";
        }
        // Look up user in database using username they provided in the form
        User existingUser = userRepository.findByUsername(registrationFormDTO.getUsername());

        // Send user back to form if username already exists
        if (existingUser != null) {
            errors.rejectValue(
                    "username",
                    "username.alreadyexists",
                    "A user with that username already exists"
            );
            return "register";
        }

        // Send user back to form if passwords didn't match
        String password = registrationFormDTO.getPassword();
        String verifyPassword = registrationFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue(
                    "password",
                    "passwords.mismatch",
                    "Passwords do not match");
            return "register";
        }

        // OTHERWISE, save new username and hashed password in database, start a new session, and redirect to home page
        User newUser = new User(registrationFormDTO.getUsername(), registrationFormDTO.getPassword());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);
        return "redirect:/profile";

    }

    // Handlers for login form
    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        model.addAttribute(new LoginFormDTO());
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   Errors errors,
                                   HttpServletRequest request) {

        // Send user back to form if errors are found
        if (errors.hasErrors()) {
            return "login";
        }

        // Look up user in database using username they provided in the form
        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());

        // Get the password the user supplied in the form
        String password = loginFormDTO.getPassword();

        // Send user back to form if username does not exist OR if password hash doesn't match
        // "Security through obscurity" — don't reveal which one was the problem
        if (theUser == null || !theUser.isMatchingPassword(password)) {
            errors.rejectValue(
                    "password",
                    "login.invalid",
                    "Credentials invalid. Please try again with correct username/password combination."
            );
            return "login";
        }

        // OTHERWISE, create a new session for the user and take them to the home page
        setUserInSession(request.getSession(), theUser);
        return "redirect:/profile";
    }

    // Handler for logout
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }

    // Handler for logout
    @GetMapping("/profile")
    public String index(){
        return "profile";
    }

}