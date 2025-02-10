package com.notsauce.parkd.models;

import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class LandingService {

    public List<Park> getShuffledNatParks(NpsResponse npsResponse) {
        List<Park> parks = npsResponse.getData();

        List<String> validDesignations = List.of("National Park", "National Parks", "National Park & Preserve", "National and State Parks");
        //National Park, National Parks, National Park & Preserve, National and State Parks,


        List<String> validParkCodes = List.of("npsa");
        //npsa (American Samoa)

        // Filter parks
        List<Park> nationalParks = parks.stream()
                .filter(park -> validDesignations.contains(park.getDesignation()) || (park.getDesignation() == null && validParkCodes.contains(park.getParkCode())))
                .collect(Collectors.toList());

        // Shuffle parks
        Collections.shuffle(nationalParks);

        return nationalParks.size() > 5 ? nationalParks.subList(0, 5) : nationalParks;
    }
}
