package com.notsauce.parkd.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.notsauce.parkd.models.ParkdUser;
import com.notsauce.parkd.models.data.ParkdUserRepository;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/parkd-users")
public class ParkdUserSearchController {

    @Autowired
    private ParkdUserRepository userRepository;
    
    @GetMapping("/search")
    public String searchUsers(@RequestParam(required = false) String username,
                            @RequestParam(required = false) String email,
                            Model model) {
        List<ParkdUser> results = new ArrayList<>();
        if (username != null) {
            results = userRepository.findByUsernameContainingIgnoreCase(username);
        } else if (email != null) {
            results = userRepository.findByEmailContainingIgnoreCase(email);
        }
        model.addAttribute("users", results);
        return "user/search";
    }
}



