package com.notsauce.parkd.controllers;

import com.notsauce.parkd.models.Comment;
import com.notsauce.parkd.models.Park;
import com.notsauce.parkd.models.Review;
import com.notsauce.parkd.models.User;
import com.notsauce.parkd.models.data.CommentRepository;
import com.notsauce.parkd.models.data.ParkRepository;
import com.notsauce.parkd.models.data.ReviewRepo;
import com.notsauce.parkd.models.data.UserRepository;
import jakarta.servlet.http.HttpSession;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepo reviewRepo;

    ///parkcard/{parkCode}
    @GetMapping("reviews/submit/{parkCode}")
    public String displaySubmitForm(Model model, @PathVariable String parkCode) {
        Optional<Park> optionalPark = parkRepository.findById(parkCode);

        if (optionalPark.isPresent()) {
            Park park = optionalPark.get();


            model.addAttribute("park", park);
            model.addAttribute("comment", new Comment());
            model.addAttribute("review", new Review());
        }
        return "reviews/submit";
    }

    @PostMapping("reviews/submit/{parkCode}")
    public String submitCommentToPark(@PathVariable String parkCode, Comment comment, Review review, Errors errors, HttpSession session) {

        Park aPark = new Park();
        if (errors.hasErrors()) {
            return "redirect:/parks/parkcard/" + parkCode;
        }

        Optional<Park> optionalPark = parkRepository.findById(parkCode);
        if (optionalPark.isPresent()) {
            aPark = optionalPark.get();

            Optional<User> optionalUser = userRepository.findById((Integer) session.getAttribute("user"));
            if (optionalUser.isPresent()) {
                User aUser = optionalUser.get();
                comment.setUser(aUser);
                review.setUserReview(aUser);
            }

            comment.setPark(aPark);
            review.setParkReview(aPark);
            commentRepository.save(comment);
            reviewRepo.save(review);
        }
        return "redirect:/parks/parkcard/" + parkCode;
}

    @GetMapping("reviews/edit/{commentId}")
    public String displayEditComment(Model model, @PathVariable int commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isPresent()) {
            Comment aComment = optionalComment.get();

            model.addAttribute("comment", aComment);

        }
        return "reviews/edit";
    }

    @PostMapping("reviews/edit/{commentId}")
    public String changeCommentContent(@PathVariable int commentId, @RequestParam String commentUpdate) {
        Comment aComment = new Comment();
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isPresent()) {
             aComment = optionalComment.get();
            aComment.setInput(commentUpdate);
            commentRepository.save(aComment);
        }
        return "redirect:/parks/parkCard/" + aComment.getPark().getParkCode();
    }

    @GetMapping("reviews/delete/{commentId}")
    public String deleteComment(@PathVariable int commentId) {

        Comment aComment = new Comment();
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isPresent()) {
            aComment = optionalComment.get();
            commentRepository.delete(aComment);
        }
        return "redirect:/parks/parkCard/" + aComment.getPark().getParkCode();
    }

}

