package JournalApp.MyFirstApp.Service;

import JournalApp.MyFirstApp.Entry.UsersEntry;
import JournalApp.MyFirstApp.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Disabled
public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsernameTest() {
        UsersEntry mockUser = new UsersEntry();
        mockUser.setUsername("prajwal");
        mockUser.setPassword("hii");
        mockUser.setRoles(new ArrayList<>()); // ✅ Important fix

        when(userRepository.findByUsername("prajwal")).thenReturn(mockUser);

        UserDetails user = userDetailsService.loadUserByUsername("prajwal");

        assertNotNull(user);
        assertEquals("prajwal", user.getUsername());
    }
}
