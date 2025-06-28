package JournalApp.MyFirstApp.Repository;

import JournalApp.MyFirstApp.Entry.UsersEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UsersEntry, ObjectId> {
    UsersEntry findByUsername(String username);
    UsersEntry deleteByUsername(String username);
}
