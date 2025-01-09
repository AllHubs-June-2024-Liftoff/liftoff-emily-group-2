package com.notsauce.parkd.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notsauce.parkd.models.NpsCamResponse;
import com.notsauce.parkd.models.NpsResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ObjectMapperDemo {
    public NpsResponse readJsonWithObjectMapper() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        URL parkRequest = new URL("https://developer.nps.gov/api/v1/parks?limit=600&start=0&api_key=CUzkMTnNk745wAFn8zcHRo8NqXbEFmUglCDLbgmC"); //Limit set to 65 TODO: adjust limit higher?


        //@@@ URL webcamRequest = new URL("https://developer.nps.gov/api/v1/webcams?parkCode=glac&limit=220&api_key=CUzkMTnNk745wAFn8zcHRo8NqXbEFmUglCDLbgmC");
                //216 total webcams among all NPS sites

        //Limit may or may not need to be higher, or change where the filtering occurs
        // ## Below Code Doesn't Function - keeping for now for reference
        //String designation = "National%20Park"; //"National+Park" doesn't work either
        //URL url = new URL("https://developer.nps.gov/api/v1/parks?limit=600&start=0&api_key=CUzkMTnNk745wAFn8zcHRo8NqXbEFmUglCDLbgmC" + "designation=" + designation);

        //Parks
        HttpURLConnection parkConnection = (HttpURLConnection) parkRequest.openConnection();
        parkConnection.setRequestMethod("GET");

        NpsResponse parkResponse = objectMapper.readValue(parkConnection.getInputStream(), NpsResponse.class);
        parkConnection.disconnect();

        //Webcams
        //@@@ HttpURLConnection webcamConnection = (HttpURLConnection) webcamRequest.openConnection();
        //@@@ webcamConnection.setRequestMethod("GET");

        //@@@ NpsResponse webcamResponse = objectMapper.readValue(webcamConnection.getInputStream(), NpsResponse.class);
        //@@@ webcamConnection.disconnect();

        return parkResponse;

    }
    /* public NpsCamResponse readJsonWithObjectMapper() throws IOException { //Do I need readJsonWithObjectMapper here?
        ObjectMapper objectMapper = new ObjectMapper();

        URL webcamRequest = new URL("https://developer.nps.gov/api/v1/webcams?parkCode=glac&limit=220&api_key=CUzkMTnNk745wAFn8zcHRo8NqXbEFmUglCDLbgmC");

        HttpURLConnection webcamConnection = (HttpURLConnection) webcamRequest.openConnection();
        webcamConnection.setRequestMethod("GET");

        NpsCamResponse webcamResponse = objectMapper.readValue(webcamConnection.getInputStream(), NpsCamResponse.class);
        webcamConnection.disconnect();

        return webcamResponse;
    }
*/
}
