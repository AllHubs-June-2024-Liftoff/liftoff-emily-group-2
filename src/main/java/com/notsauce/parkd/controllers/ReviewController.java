package com.notsauce.parkd.controllers;

import com.notsauce.parkd.models.Comment;
import com.notsauce.parkd.models.Park;
import com.notsauce.parkd.models.User;
import com.notsauce.parkd.models.data.CommentRepository;
import com.notsauce.parkd.models.data.ParkRepository;
import com.notsauce.parkd.models.data.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller

public class ReviewController {


    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ParkRepository parkRepository;
    @Autowired
    private UserRepository userRepository;

    ///parkcard/{parkCode}
    @GetMapping("reviews/submit/{parkCode}")
    public String displaySubmitForm(Model model, @PathVariable String parkCode) {
        Optional<Park> optionalPark = parkRepository.findById(parkCode);
        if (optionalPark.isPresent()) {
            Park park = optionalPark.get();
            model.addAttribute("park", park);
            model.addAttribute("comment", new Comment());

        }
        return "reviews/submit";
    }

    @PostMapping("reviews/submit/{parkCode}")
    public String submitCommentToPark(@PathVariable String parkCode, Comment comment, Errors errors, HttpSession session) {

        if (errors.hasErrors()) {
            return "parks/parkcard/" + parkCode ;
        }

        Optional<Park> optionalPark = parkRepository.findById(parkCode);
        if (optionalPark.isPresent()) {
            Park park = optionalPark.get();

            Optional<User> optionalUser = userRepository.findById((Integer) session.getAttribute("user"));
            if (optionalUser.isPresent()) {
                User aUser = optionalUser.get();

                comment.setUser(aUser);
            }

            comment.setPark(park);
            commentRepository.save(comment);
        }
      return  "parks/parkcard/" + parkCode ;
}
}

