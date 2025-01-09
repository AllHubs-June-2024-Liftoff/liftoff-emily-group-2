package com.notsauce.parkd.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notsauce.parkd.models.NpsCamResponse;
import com.notsauce.parkd.models.NpsResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebcamMapper {
    public NpsCamResponse readJsonWithWebcamMapper() throws IOException { //Do I need readJson... here?
        ObjectMapper objectMapper = new ObjectMapper();

        URL webcamRequest = new URL("https://developer.nps.gov/api/v1/webcams?parkCode=glac&limit=220&api_key=CUzkMTnNk745wAFn8zcHRo8NqXbEFmUglCDLbgmC");

        HttpURLConnection webcamConnection = (HttpURLConnection) webcamRequest.openConnection();
        webcamConnection.setRequestMethod("GET");

        NpsCamResponse webcamResponse = objectMapper.readValue(webcamConnection.getInputStream(), NpsCamResponse.class);
        webcamConnection.disconnect();

        return webcamResponse;
    }

}
