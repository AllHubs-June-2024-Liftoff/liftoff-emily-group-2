package com.notsauce.parkd.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notsauce.parkd.models.NpsCamResponse;
import com.notsauce.parkd.models.NpsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class WebcamMapper {

        private static final String API_URL = "https://developer.nps.gov/api/v1/webcams";


    public NpsCamResponse readJsonWithWebcamMapper(String parkCode) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        //Just straight up threw the API key in there for the moment
        String fullUrl = String.format("%s?parkCode=%s&limit=220&api_key=%s", API_URL, parkCode, "CUzkMTnNk745wAFn8zcHRo8NqXbEFmUglCDLbgmC");
        //216 total webcams among all NPS sites

        URL webcamRequest = new URL(fullUrl);

        HttpURLConnection webcamConnection = (HttpURLConnection) webcamRequest.openConnection();
        webcamConnection.setRequestMethod("GET");

        NpsCamResponse webcamResponse = objectMapper.readValue(webcamConnection.getInputStream(), NpsCamResponse.class);
        webcamConnection.disconnect();

        return webcamResponse;
    }

}

//URL webcamRequest = new URL("https://developer.nps.gov/api/v1/webcams?parkCode=olym&limit=220&api_key=CUzkMTnNk745wAFn8zcHRo8NqXbEFmUglCDLbgmC");