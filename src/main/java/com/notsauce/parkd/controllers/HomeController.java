package com.notsauce.parkd.controllers;

import com.notsauce.parkd.mapper.ObjectMapperDemo;
import com.notsauce.parkd.mapper.WebcamMapper;
import com.notsauce.parkd.models.NpsCamResponse;
import com.notsauce.parkd.models.NpsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class HomeController {




    @GetMapping("/")
    public String index(Model model) {
        ObjectMapperDemo objectMapperDemo = new ObjectMapperDemo();
        NpsResponse response;
        try {
            response = objectMapperDemo.readJsonWithObjectMapper();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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

    // TODO: Following Code Has Issues Probably 1x16:

    @GetMapping("/parkcard")
    public String parkcard(Model model) {
        ObjectMapperDemo objectMapperDemo = new ObjectMapperDemo();
        NpsResponse parkResponse;
        WebcamMapper webcamMapper = new WebcamMapper();
        NpsCamResponse webcamResponse;
        try {
            parkResponse = objectMapperDemo.readJsonWithObjectMapper();
            webcamResponse = webcamMapper.readJsonWithWebcamMapper();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("npsResponse", parkResponse);
        model.addAttribute("npsCamResponse", webcamResponse);

        return "parkcard";
    }


}