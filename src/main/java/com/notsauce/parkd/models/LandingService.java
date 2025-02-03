package com.notsauce.parkd.models;

import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class LandingService {

    public List<Park> getShuffledNatParks(NpsResponse npsResponse) {
        List<Park> parks = npsResponse.getData();

        // Filter parks
        List<Park> nationalParks = parks.stream()
                .filter(park -> "National Park".equals(park.getDesignation()))
                .collect(Collectors.toList());

        // Shuffle parks
        Collections.shuffle(nationalParks);

        return nationalParks.size() > 5 ? nationalParks.subList(0, 5) : nationalParks;
    }
}
