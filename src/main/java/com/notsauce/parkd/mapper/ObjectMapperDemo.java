package com.notsauce.parkd.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notsauce.parkd.models.NpsResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

//TBH REALLY SHOULD BE CALLED PARKMAPPER BUT WE'RE IN TOO DEEP
//Comment to commit

public class ObjectMapperDemo {
    public NpsResponse readJsonWithObjectMapper() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        URL parkRequest = new URL("https://developer.nps.gov/api/v1/parks?limit=1000&start=0&api_key=CUzkMTnNk745wAFn8zcHRo8NqXbEFmUglCDLbgmC"); //Limit set to 65 TODO: adjust limit higher?

        //Parks
        HttpURLConnection parkConnection = (HttpURLConnection) parkRequest.openConnection();
        parkConnection.setRequestMethod("GET");

        NpsResponse parkResponse = objectMapper.readValue(parkConnection.getInputStream(), NpsResponse.class);
        parkConnection.disconnect();

        return parkResponse;

    }
}
