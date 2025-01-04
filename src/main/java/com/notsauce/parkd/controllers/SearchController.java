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

    @RequestMapping("/")
    public String index(Model model) {
        ObjectMapperDemo objectMapperDemo = new ObjectMapperDemo();
        NpsResponse response;
        List<Park> parksToRender = new ArrayList<>();
        Set<String> states = new HashSet<>();



        try {
            response = objectMapperDemo.readJsonWithObjectMapper();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

// splits comma separated values into single values and adds them to an array.
        for (Park park : response.getData()) {
            String[] statesPerPark =  park.getStates().split(",");
            states.addAll(Arrays.asList(statesPerPark));
        }

        model.addAttribute("states", states);

        return "search";
    }

    @PostMapping("results")
    public String searchForPark(Model model, @RequestParam String searchTerm ) {
        ObjectMapperDemo objectMapperDemo = new ObjectMapperDemo();
        NpsResponse response;
        List<Park> parksToRender = new ArrayList<>();
        Set<String> states = new HashSet<>();
        String fullName = "";
        String stateCode = "";

        try {
            response = objectMapperDemo.readJsonWithObjectMapper();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // splits comma separated values into single values and adds them to an array.
        for (Park park : response.getData()) {
            String[] statesPerPark =  park.getStates().split(",");
            states.addAll(Arrays.asList(statesPerPark));
        }

        List<String> userInput = Arrays.asList(searchTerm.split(","));


        if (searchTerm.isEmpty()) {
            parksToRender = response.getData();
        }

        for (Park park : response.getData()) {
            if ((park.getFullName().toLowerCase()).contains(searchTerm.toLowerCase())) {
                parksToRender.add(park);
            }
        }

        //sout for debugging
        System.out.println("-------------------------");
        System.out.println("searchTerm : " + searchTerm);
        System.out.println("Full searchTem : "  + userInput);
        System.out.println("fullName Search : "  + fullName);
        System.out.println("State Code search : "  + stateCode);
        System.out.println("-------------------------");

        model.addAttribute("parks", parksToRender);

        return "search";
    }

}