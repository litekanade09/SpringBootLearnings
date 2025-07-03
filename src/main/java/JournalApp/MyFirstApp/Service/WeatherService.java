package JournalApp.MyFirstApp.Service;


import JournalApp.MyFirstApp.ApiWeather.ApiResponse;
import JournalApp.MyFirstApp.Cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    private static final String apikey="";

    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public ApiResponse.Root getWeather(String city){
        if (appCache.app_cache == null || !appCache.app_cache.containsKey("weather_api")) {
            System.err.println("Weather API URL is missing in app_cache!"); // 🔍 DEBUG
            throw new RuntimeException("Weather API URL is not configured.");
        }

        String finalpi = appCache.app_cache.get("weather_api")
                .replace("CITY", city)
                .replace("API_KEY", apikey);


        System.out.println("Calling weather API: " + finalpi); // 🔍 DEBUG

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ApiResponse.Root> response = restTemplate.exchange(finalpi, HttpMethod.GET, null, ApiResponse.Root.class);
        return response.getBody();
    }


}
