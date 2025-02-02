package com.notsauce.parkd.controllers;

import com.notsauce.parkd.mapper.ActivityMapper;
import com.notsauce.parkd.mapper.ObjectMapperDemo;
import com.notsauce.parkd.mapper.WebcamMapper;
import com.notsauce.parkd.models.NpsActivitiesParksResponse;
import com.notsauce.parkd.models.NpsCamResponse;
import com.notsauce.parkd.models.NpsResponse;
import com.notsauce.parkd.models.data.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class HomeController {


@Autowired
private ParkRepository parkRepository;

    @GetMapping("/")
    public String index(Model model) {
        ObjectMapperDemo objectMapperDemo = new ObjectMapperDemo();
        NpsResponse response;
        try {
            response = objectMapperDemo.readJsonWithObjectMapper();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
parkRepository.saveAll(response.getData());

        model.addAttribute("npsResponse", response);
        return "index";
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

        model.addAttribute("npsResponse", response);
        return "landing";
    }

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
        //
        WebcamMapper webcamMapper = new WebcamMapper();
        NpsCamResponse webcamResponse;
        //
        ActivityMapper activityMapper = new ActivityMapper();
        NpsActivitiesParksResponse activityResponse;
        try {
            parkResponse = objectMapperDemo.readJsonWithObjectMapper();
            webcamResponse = webcamMapper.readJsonWithWebcamMapper();
            activityResponse = activityMapper.readJsonWithActivityMapper();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("npsResponse", parkResponse);
        model.addAttribute("npsCamResponse", webcamResponse);
        model.addAttribute("npsAPResponse", activityResponse);

        return "parkcarddemo";
    }


}