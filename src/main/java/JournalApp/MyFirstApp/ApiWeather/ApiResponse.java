package JournalApp.MyFirstApp.ApiWeather;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {

    @Getter
    @Setter
    public static class Current {
        public double temp_c;
        public double temp_f;
    }

    @Getter
    @Setter
    public static class Location {
        public String name;
        public String region;
        public String country;
    }

    @Getter
    @Setter
    public static class Root {
        public Location location;
        public Current current;
    }
}
