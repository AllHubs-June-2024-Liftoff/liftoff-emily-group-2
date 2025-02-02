package com.notsauce.parkd.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notsauce.parkd.models.NpsActivitiesParksResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ActivityMapper {
    public NpsActivitiesParksResponse readJsonWithActivityMapper() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        URL activityRequest = new URL("https://developer.nps.gov/api/v1/activities/parks?id=A59947B7-3376-49B4-AD02-C0423E08C5F7&limit=50&api_key=CUzkMTnNk745wAFn8zcHRo8NqXbEFmUglCDLbgmC"); //Pulls out nearly 33k lines of JSON

        HttpURLConnection activityConnection = (HttpURLConnection) activityRequest.openConnection();
        activityConnection.setRequestMethod("GET");

        NpsActivitiesParksResponse activityResponse = objectMapper.readValue(activityConnection.getInputStream(), NpsActivitiesParksResponse.class);
        activityConnection.disconnect();

        return activityResponse;

    }
}
