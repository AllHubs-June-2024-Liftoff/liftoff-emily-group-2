package com.notsauce.parkd.controllers;

import com.notsauce.parkd.models.Comment;
import com.notsauce.parkd.models.Park;
import com.notsauce.parkd.models.data.CommentRepository;
import com.notsauce.parkd.models.data.ParkRepository;
import jakarta.validation.Valid;
import org.hibernate.annotations.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller

public class ReviewController {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ParkRepository parkRepository;

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
    public String submitCommentToPark(@PathVariable String parkCode, Comment comment, Errors errors) {

        if (errors.hasErrors()) {
            return "parks/parkcard/" + parkCode ;
        }

        Optional<Park> optionalPark = parkRepository.findById(parkCode);
        if (optionalPark.isPresent()) {
            Park park = optionalPark.get();

            comment.setPark(park);
            commentRepository.save(comment);
        }
      return  "parks/parkcard/" + parkCode ;
}
}

