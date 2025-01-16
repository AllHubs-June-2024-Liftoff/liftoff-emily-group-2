package com.notsauce.parkd.controllers;

import com.notsauce.parkd.models.Comment;
import com.notsauce.parkd.models.Park;
import com.notsauce.parkd.models.Review;
import com.notsauce.parkd.models.User;
import com.notsauce.parkd.models.data.CommentRepository;
import com.notsauce.parkd.models.data.ParkRepository;
import com.notsauce.parkd.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("parks")
public class ParkController {

@Autowired
private ParkRepository parkRepository;

    @GetMapping("/parkcard/{parkCode}")
    public String displayViewPark(Model model, @PathVariable String parkCode) {
        Optional<Park> optionalPark = parkRepository.findById(parkCode);
        if (optionalPark.isPresent()) {
            Park park = optionalPark.get();


            List<Review> reviews = park.getReviews();
            List<Integer> fives = new ArrayList<>();
            List<Integer> fours = new ArrayList<>();
            List<Integer> threes  = new ArrayList<>();
            List<Integer> twos = new ArrayList<>();
            List<Integer> ones = new ArrayList<>();
            int totalNumberOfVotes = 0;

            for (Review review : reviews) {
                totalNumberOfVotes ++;

                if(review.getReview() == 5) {
                    fives.add(review.getReview());
                }
                if(review.getReview() == 4) {
                    fours.add(review.getReview());
                }if(review.getReview() == 3) {
                    threes.add(review.getReview());
                }if(review.getReview() == 2) {
                    twos.add(review.getReview());
                }if(review.getReview() == 1) {
                    ones.add(review.getReview());
                }
            }

            int fivesWeight = fives.size() * 5;
            int foursWeight = fours.size() * 4;
            int threesWeight = threes.size() * 3;
            int twosWeight = twos.size() * 2;
            int onesWeight = ones.size();

            double starScore = (double) (fivesWeight + foursWeight + threesWeight + twosWeight + onesWeight) / (totalNumberOfVotes);

            model.addAttribute("comment", park.getComments());
            model.addAttribute("park", park);
            model.addAttribute("starScore", starScore);
        }

        return "parks/parkcard";
    }

}
