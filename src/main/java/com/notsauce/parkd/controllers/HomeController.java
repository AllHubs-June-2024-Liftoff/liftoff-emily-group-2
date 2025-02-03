package com.notsauce.parkd.controllers;

import com.notsauce.parkd.models.User;
import com.notsauce.parkd.mapper.ActivityMapper;
import com.notsauce.parkd.mapper.ObjectMapperDemo;
import com.notsauce.parkd.mapper.WebcamMapper;
import com.notsauce.parkd.models.NpsActivitiesParksResponse;
import com.notsauce.parkd.models.NpsCamResponse;
import com.notsauce.parkd.models.NpsResponse;
import com.notsauce.parkd.models.data.ParkRepository;
import com.notsauce.parkd.models.data.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParkRepository parkRepository;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        ObjectMapperDemo objectMapperDemo = new ObjectMapperDemo();
        NpsResponse response;
        try {
            response = objectMapperDemo.readJsonWithObjectMapper();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
parkRepository.saveAll(response.getData());

        Integer userId = (Integer) session.getAttribute("user");
        if (userId != null) {
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isPresent()) {
                User currentUser = optionalUser.get();

                model.addAttribute("currentUser", currentUser);
                model.addAttribute("totalReviews", currentUser.getReviews().size());
            }

        }
        model.addAttribute("npsResponse", response);

        return "index";
    }


    //MOVED TO LANDING CONTROLLER
//    @GetMapping("/landing")
//    public String landing(Model model) {
//        ObjectMapperDemo objectMapperDemo = new ObjectMapperDemo();
//        NpsResponse response;
//        try {
//            response = objectMapperDemo.readJsonWithObjectMapper();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        model.addAttribute("npsResponse", response);
//        return "landing";
//    }

    @GetMapping("/explore")
    public String explore(Model model) {
        ObjectMapperDemo objectMapperDemo = new ObjectMapperDemo();
        NpsResponse response;
        try {
            response = objectMapperDemo.readJsonWithObjectMapper();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("npsResponse", response);
        return "explore";
    }

    @GetMapping("/parkcarddemo")
    public String parkcard(Model model) {
        ObjectMapperDemo objectMapperDemo = new ObjectMapperDemo();
        NpsResponse parkResponse;
        //Broken for the Greater Good
        //WebcamMapper webcamMapper = new WebcamMapper();
        NpsCamResponse webcamResponse;
        //
        ActivityMapper activityMapper = new ActivityMapper();
        NpsActivitiesParksResponse activityResponse;
        try {
            parkResponse = objectMapperDemo.readJsonWithObjectMapper();
            //Following is Currently Broken?
            // webcamResponse = webcamMapper.readJsonWithWebcamMapper();
            activityResponse = activityMapper.readJsonWithActivityMapper();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("npsResponse", parkResponse);
        //Also Broken for the greater good
        //model.addAttribute("npsCamResponse", webcamResponse);
        model.addAttribute("npsAPResponse", activityResponse);

        return "parkcarddemo";
    }


}