package JournalApp.MyFirstApp.controller;


import JournalApp.MyFirstApp.Entry.UsersEntry;
import JournalApp.MyFirstApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/alluser")
    public ResponseEntity<?> getAllUsers(){
        List<UsersEntry> all = userService.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return null;
    }

    @PostMapping("/createadmin")
    public void createAdminUser(@RequestBody UsersEntry user){
        userService.SaveAdmin(user);
    }
}
