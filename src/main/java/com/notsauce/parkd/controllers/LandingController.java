package com.notsauce.parkd.controllers;

import com.notsauce.parkd.models.LandingService;
import com.notsauce.parkd.models.NpsResponse;
import com.notsauce.parkd.models.Park;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.notsauce.parkd.mapper.ObjectMapperDemo;

import java.io.IOException;
import java.util.List;

@Controller
public class LandingController {
    private /*final goes here maybe?*/ LandingService landingService;

    @Autowired
    public LandingController(LandingService landingService) {
        this.landingService = landingService;
    }

    @GetMapping("/landing")
    public String landing(Model model) {
        ObjectMapperDemo objectMapperDemo = new ObjectMapperDemo();
        NpsResponse response;
        try {
            response = objectMapperDemo.readJsonWithObjectMapper();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Park> shuffledNationalParks = landingService.getShuffledNatParks(response);

        model.addAttribute("shuffledNationalParks", shuffledNationalParks);
        model.addAttribute("npsResponse", response);

        return "landing";
    }
}

