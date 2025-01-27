package com.notsauce.parkd.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.notsauce.parkd.models.User;
import com.notsauce.parkd.models.data.UserRepository;

@Controller
@RequestMapping("parkdusersearch")
public class ParkdUserSearch {

    @Autowired 
    private UserRepository userRepository;

    @GetMapping("")
    public String searchUsers(
            @RequestParam(required = false) String searchTerm,
            Model model) {

        List<User> results = new ArrayList<>();

        if (searchTerm != null && !searchTerm.isEmpty()) {
            // Search by username 
            results = userRepository.findByUsernameContainingIgnoreCase(searchTerm);
            

            // Remove duplicates
            results = results.stream().distinct().collect(Collectors.toList());
        } 

        model.addAttribute("users", results);
        return "parkdusersearch";
    }
    
}
