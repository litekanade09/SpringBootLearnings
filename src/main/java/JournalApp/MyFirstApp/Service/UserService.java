package JournalApp.MyFirstApp.Service;

import JournalApp.MyFirstApp.Entry.UsersEntry;
import JournalApp.MyFirstApp.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void SaveNewUser(UsersEntry Users){
        Users.setPassword(passwordEncoder.encode(Users.getPassword()));
        Users.setRoles(Arrays.asList("User"));
        userRepository.save(Users);
    }
    public void SaveAdmin(UsersEntry Users){
        Users.setPassword(passwordEncoder.encode(Users.getPassword()));
        Users.setRoles(Arrays.asList("User","ADMIN"));
        userRepository.save(Users);
    }

    public void SaveUser(UsersEntry Users){

        userRepository.save(Users);
    }
    public List<UsersEntry> getAll(){
        return userRepository.findAll();
    }
    public Optional<UsersEntry> findById(ObjectId id){
        return userRepository.findById(id);
    }
    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }
    public UsersEntry findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
