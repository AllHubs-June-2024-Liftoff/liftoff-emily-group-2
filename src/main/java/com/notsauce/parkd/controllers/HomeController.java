package com.notsauce.parkd.controllers;

import com.notsauce.parkd.mapper.ObjectMapperDemo;
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


}