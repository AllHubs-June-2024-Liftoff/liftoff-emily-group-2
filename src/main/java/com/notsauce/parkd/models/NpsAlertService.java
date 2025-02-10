package com.notsauce.parkd.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notsauce.parkd.models.data.ParkRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class NpsAlertService {

    @Value("${app.api.key}")
    private String apiKey;

    private static final String API_URL = "https://developer.nps.gov/api/v1/alerts";

    private final ParkRepository parkRepository;

    public NpsAlertService(ParkRepository parkRepository) {
        this.parkRepository = parkRepository;
    }

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

            List<Alert> alerts = response.getData();

            for (Alert alert : alerts) {
                Park park = parkRepository.findByparkCode(alert.getParkCode());
                if (park != null) {
                    alert.setParkName(park.getFullName());
                } else {
                    alert.setParkName("Unknown Park");
                }
            }

            return alerts;
        } finally {
            connection.disconnect();
        }
    }
}

