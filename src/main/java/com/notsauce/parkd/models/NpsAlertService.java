package com.notsauce.parkd.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class NpsAlertService {

    @Value("${nps.api.key}")
    private String apiKey;

    private static final String API_URL = "https://developer.nps.gov/api/v1/alerts";

    public List<Alert> getAlerts() throws IOException {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("API Key is not configured properly.");
        }

        String fullUrl = String.format("%s?api_key=%s", API_URL, apiKey);
        URL url = new URL(fullUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (var inputStream = connection.getInputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            NpsAlertResponse response = objectMapper.readValue(inputStream, NpsAlertResponse.class);
            return response.getData();
        } finally {
            connection.disconnect();
        }
    }
}
