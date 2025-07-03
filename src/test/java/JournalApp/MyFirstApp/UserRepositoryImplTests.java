package JournalApp.MyFirstApp;

import JournalApp.MyFirstApp.Repository.UserRepository;
import JournalApp.MyFirstApp.Repository.UserRepositoryImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class UserRepositoryImplTests {

	@Autowired
	private UserRepositoryImpl userRepositoryImpl;

	@Test
	public void testSaveNewUser(){
		userRepositoryImpl.getUsersSA();
	}
}
