package JournalApp.MyFirstApp.controller;

import JournalApp.MyFirstApp.Entry.UsersEntry;
import JournalApp.MyFirstApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @GetMapping("/health")
    public String health(){
        return "ok";
    }
    @PostMapping("/create-user")
    public void createUser(@RequestBody UsersEntry user){
        userService.SaveNewUser(user);
    }
}
