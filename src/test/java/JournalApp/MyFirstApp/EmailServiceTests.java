package JournalApp.MyFirstApp;

import JournalApp.MyFirstApp.Service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendEmail() {
        emailService.sendEmail(
                "prajwalkanade648@gmail.com",
                "Testing Java Mail Sender",
                "Hattttt saala kanatal gaya, lekin majja aaraha hai 😄"
        );
    }
}
