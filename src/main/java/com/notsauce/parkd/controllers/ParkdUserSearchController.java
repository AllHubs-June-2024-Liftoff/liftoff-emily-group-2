package com.notsauce.parkd.controllers;

 

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.notsauce.parkd.models.ParkdUser;
import com.notsauce.parkd.models.data.ParkdUserRepository;

import org.springframework.ui.Model;

@Controller
@RequestMapping("parkdusersearch")
public class ParkdUserSearchController {

    @Autowired
    private ParkdUserRepository userRepository;

    @GetMapping("")
    public String searchUsers(
            @RequestParam(required = false) String searchTerm,
            Model model) {

        List<ParkdUser> results = new ArrayList<>();

        if (searchTerm != null && !searchTerm.isEmpty()) {
            // Search by username or email
            results = userRepository.findByUsernameContainingIgnoreCase(searchTerm);
            results.addAll(userRepository.findByEmailContainingIgnoreCase(searchTerm));

            // Remove duplicates
            results = results.stream().distinct().collect(Collectors.toList());
        } //else {
            // Fetch all users if no search term
           // results = userRepository.findAll();
        //}

        model.addAttribute("users", results);
        return "parkdusersearch";
    }
}
