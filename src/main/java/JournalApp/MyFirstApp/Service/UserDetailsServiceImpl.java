package JournalApp.MyFirstApp.Service;

import JournalApp.MyFirstApp.Entry.UsersEntry;
import JournalApp.MyFirstApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UsersEntry user = userRepository.findByUsername(username);
        if(user != null){
            UserDetails userDetail = org.springframework.security.core.userdetails.User.builder().
                    username(user.getUsername())
                    .password(user.getPassword()).roles(user.getRoles().toArray(new String[0]))
                    .build();
            return userDetail;
        }
        return null;
    }
}
