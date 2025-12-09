import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class LocationIQService {

    @Value("${locationiq.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    // ✔ Distance calculation
    public double getDistance(String source, String destination) {
        try {
            String sUrl = "https://us1.locationiq.com/v1/search?key=" + apiKey +
                    "&q=" + source + "&format=json";

            JsonNode sNode = mapper.readTree(restTemplate.getForObject(sUrl, String.class)).get(0);
            double sLat = sNode.get("lat").asDouble();
            double sLon = sNode.get("lon").asDouble();

            String dUrl = "https://us1.locationiq.com/v1/search?key=" + apiKey +
                    "&q=" + destination + "&format=json";

            JsonNode dNode = mapper.readTree(restTemplate.getForObject(dUrl, String.class)).get(0);
            double dLat = dNode.get("lat").asDouble();
            double dLon = dNode.get("lon").asDouble();

            String disUrl = "https://us1.locationiq.com/v1/directions/driving/" +
                    sLon + "," + sLat + ";" + dLon + "," + dLat + "?key=" + apiKey;

            JsonNode distNode = mapper.readTree(restTemplate.getForObject(disUrl, String.class));

            double meters = distNode.get("routes").get(0).get("distance").asDouble();

            return meters / 1000;

        } catch (Exception e) {
            return 0;
        }
    }

    // ✔ Reverse geocoding (needed by DriverService)
    public String getAddressFromCoordinates(double lat, double lon) {
        try {
            String url = "https://us1.locationiq.com/v1/reverse?key=" + apiKey +
                    "&lat=" + lat + "&lon=" + lon + "&format=json";

            JsonNode response = mapper.readTree(restTemplate.getForObject(url, String.class));

            return response.get("display_name").asText();

        } catch (Exception e) {
            return "Unknown Location";
        }
    }
}
