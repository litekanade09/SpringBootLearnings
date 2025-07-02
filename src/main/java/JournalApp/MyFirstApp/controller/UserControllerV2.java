package JournalApp.MyFirstApp.controller;

import JournalApp.MyFirstApp.ApiWeather.ApiResponse;
import JournalApp.MyFirstApp.Entry.UsersEntry;
import JournalApp.MyFirstApp.Repository.UserRepository;
import JournalApp.MyFirstApp.Service.UserService;
import JournalApp.MyFirstApp.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserControllerV2 {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UsersEntry user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UsersEntry userIndb = userService.findByUsername(username);
        userIndb.setUsername(user.getUsername());
        userIndb.setPassword(user.getPassword());
        userService.SaveNewUser(userIndb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUserById(@RequestBody UsersEntry user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greetings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ApiResponse.Root weatherResponse = weatherService.getWeather("Surat");

        String greeting = "Hiiiiiiiii " + authentication.getName();

        if (weatherResponse != null
                && weatherResponse.getCurrent() != null
                && weatherResponse.getLocation() != null) {

            double tempC = weatherResponse.getCurrent().getTemp_c();
            double tempF = weatherResponse.getCurrent().getTemp_f();
            String city = weatherResponse.getLocation().getName();
            String country = weatherResponse.getLocation().getCountry();

            greeting += ", Current temperature in " + city + ", " + country + " is " + tempC + "°C / " + tempF + "°F.";
        }

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }



}

