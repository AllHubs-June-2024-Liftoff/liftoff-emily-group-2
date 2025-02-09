package com.notsauce.parkd.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notsauce.parkd.models.NpsCamResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class WebcamMapper {

    private static final String API_URL = "https://developer.nps.gov/api/v1/webcams";


    @Value("${app.api.key}")  // Injecting the API key from the environment
    private String apiKey;


    public NpsCamResponse readJsonWithWebcamMapper(String parkCode) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String fullUrl = String.format("%s?parkCode=%s&limit=220&api_key=%s", API_URL, parkCode, apiKey);
        //216 total webcams among all NPS sites

        URL webcamRequest = new URL(fullUrl);

        HttpURLConnection webcamConnection = (HttpURLConnection) webcamRequest.openConnection();
        webcamConnection.setRequestMethod("GET");

        NpsCamResponse webcamResponse = objectMapper.readValue(webcamConnection.getInputStream(), NpsCamResponse.class);
        webcamConnection.disconnect();

        return webcamResponse;
    }

}