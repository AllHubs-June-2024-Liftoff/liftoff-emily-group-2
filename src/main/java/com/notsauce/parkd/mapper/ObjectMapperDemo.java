package com.notsauce.parkd.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notsauce.parkd.models.NpsResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ObjectMapperDemo {
    public NpsResponse readJsonWithObjectMapper() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        URL url = new URL("https://developer.nps.gov/api/v1/parks?limit=473&start=0&api_key=CUzkMTnNk745wAFn8zcHRo8NqXbEFmUglCDLbgmC"); //Limit set to 65 TODO: adjust limit higher?

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        NpsResponse response = objectMapper.readValue(connection.getInputStream(), NpsResponse.class);
        connection.disconnect();

        return response;

    }
}
