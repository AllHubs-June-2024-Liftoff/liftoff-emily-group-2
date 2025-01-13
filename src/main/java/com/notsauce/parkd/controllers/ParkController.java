package com.notsauce.parkd.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("parks")
public class ParkController {


    @GetMapping("parkcard")
    public String parkCardView(Model model){


        return "parks/parkcard";
    }

}
