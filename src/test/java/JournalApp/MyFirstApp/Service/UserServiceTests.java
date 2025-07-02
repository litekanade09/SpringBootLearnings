package JournalApp.MyFirstApp.Service;

import JournalApp.MyFirstApp.Repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@Disabled
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;
    @Test
    public void testFindByUsername(){
        assertNotNull(userRepository.findByUsername("prajwal"));
    }
}
