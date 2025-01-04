package com.notsauce.parkd.controllers;

import com.notsauce.parkd.mapper.ObjectMapperDemo;
import com.notsauce.parkd.models.NpsResponse;
import com.notsauce.parkd.models.Park;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.*;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    //    https://developer.nps.gov/api/v1/parks?
//    limit=473&start=0&
//    q=linc&api_key=CUzkMTnNk745wAFn8zcHRo8NqXbEFmUglCDLbgmC
    @RequestMapping("")
    public String index(Model model) {
        ObjectMapperDemo objectMapperDemo = new ObjectMapperDemo();
        NpsResponse response;
        List<Park> parksToRender = new ArrayList<>();
        Set<String> statesFinal = new HashSet<>();



        try {
            response = objectMapperDemo.readJsonWithObjectMapper();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

// splits comma separated values into single values and adds them to an array.
        for (Park park : response.getData()) {
            String[] statesPerPark =  park.getStates().split(",");
            statesFinal.addAll(Arrays.asList(statesPerPark));
        }

        model.addAttribute("states", statesFinal);

        return "search";
    }

    @PostMapping("results")
    public String searchForPark(Model model, @RequestParam String searchTerm ) {
        ObjectMapperDemo objectMapperDemo = new ObjectMapperDemo();
        NpsResponse response;
        List<Park> parksToRender = new ArrayList<>();
        Set<String> states = new HashSet<>();

        try {
            response = objectMapperDemo.readJsonWithObjectMapper();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        if (searchTerm.isEmpty()) {
            parksToRender = response.getData();
        }

        for (Park park : response.getData()) {
            if ((park.getFullName().toLowerCase()).contains(searchTerm.toLowerCase())) {
                parksToRender.add(park);
            }
        }

        model.addAttribute("parks", parksToRender);

        return "search";
    }

}