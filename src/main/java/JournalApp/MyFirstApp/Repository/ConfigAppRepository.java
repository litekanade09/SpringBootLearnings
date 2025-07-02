package JournalApp.MyFirstApp.Repository;

import JournalApp.MyFirstApp.Entry.ConfigAppEntity;
import JournalApp.MyFirstApp.Entry.UsersEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigAppRepository extends MongoRepository<ConfigAppEntity, ObjectId> {

}
