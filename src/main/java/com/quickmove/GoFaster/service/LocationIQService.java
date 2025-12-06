package com.quickmove.GoFaster.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocationIQService {
	
	@Value("${locationiq.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAddressFromCoordinates(double lat, double lon) {

        String url = "https://us1.locationiq.com/v1/reverse?key=" + apiKey +
                     "&lat=" + lat +
                     "&lon=" + lon +
                     "&format=json";

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response != null && response.containsKey("display_name")) {
            return response.get("display_name").toString();
        }
        return "Unknown Location";
    }

}
