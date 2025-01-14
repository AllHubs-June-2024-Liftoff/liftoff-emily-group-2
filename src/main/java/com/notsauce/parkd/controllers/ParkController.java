package com.notsauce.parkd.controllers;

import com.notsauce.parkd.models.Park;
import com.notsauce.parkd.models.data.CommentRepository;
import com.notsauce.parkd.models.data.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("parks")
public class ParkController {

@Autowired
private ParkRepository parkRepository;
@Autowired
private CommentRepository commentRepository;

    @GetMapping("/parkcard/{parkCode}")
    public String displayViewPark(Model model, @PathVariable String parkCode) {
        Optional<Park> optionalPark = parkRepository.findById(parkCode);
        if (optionalPark.isPresent()) {
            Park park = optionalPark.get();

            model.addAttribute("comment", commentRepository.findAll());
            model.addAttribute("park", park);
        }
        return "parks/parkcard";
    }

}
