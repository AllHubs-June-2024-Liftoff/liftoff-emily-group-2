package com.notsauce.parkd.controllers;

import com.notsauce.parkd.mapper.ObjectMapperDemo;
import com.notsauce.parkd.mapper.WebcamMapper;
import com.notsauce.parkd.models.*;
import com.notsauce.parkd.models.data.CommentRepository;
import com.notsauce.parkd.models.data.ParkRepository;
import com.notsauce.parkd.models.data.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("parks")
public class ParkController {

//Probably don't need this anymore/at the moment if key is hardcoded
//    private final WebcamMapper webcamMapper;
//
//@Autowired
//public ParkController(WebcamMapper webcamMapper) {
//    this.webcamMapper = webcamMapper;
//}

@Autowired
private ParkRepository parkRepository;
@Autowired
private UserRepository userRepository;

    @GetMapping("/parkcard/{parkCode}")
    public String displayViewPark(Model model, @PathVariable String parkCode, HttpSession session) {
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
            String status = "Unfavorable";

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

            if (starScore == 0) {
                status = "No Reviews...Yet";
            }
            if (starScore >= 3) {
                status = "Favorable";
            }
            Optional<User> optionalUser = userRepository.findById((Integer) session.getAttribute("user"));
            if (optionalUser.isPresent()) {
                User aUser = optionalUser.get();
                model.addAttribute("currentUser", aUser);
            }
            //Initialize Mappers
            ObjectMapperDemo objectMapperDemo = new ObjectMapperDemo();
            WebcamMapper webcamMapper = new WebcamMapper();
            //Initialize Responses
            NpsResponse parkResponse;
            NpsCamResponse webcamResponse;

            try {
                parkResponse = objectMapperDemo.readJsonWithObjectMapper();
                webcamResponse = webcamMapper.readJsonWithWebcamMapper(parkCode);
                //Activity Mapper Left Out For Now
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //
            model.addAttribute("comment", park.getComments());
            model.addAttribute("park", park);
            model.addAttribute("starScore", starScore);
            model.addAttribute("status", status);
            //Add API Data to Model
            model.addAttribute("parkCode", parkCode); //might not be needed
            model.addAttribute("npsResponse", parkResponse);
            model.addAttribute("npsCamResponse", webcamResponse);
        }

        return "parks/parkcard";
    }

}
