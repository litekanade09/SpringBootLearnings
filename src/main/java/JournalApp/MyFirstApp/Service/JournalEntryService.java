package JournalApp.MyFirstApp.Service;

import JournalApp.MyFirstApp.Entry.JournalEntry;
import JournalApp.MyFirstApp.Entry.UsersEntry;
import JournalApp.MyFirstApp.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userSerive;

    @Transactional
    public void SaveEntry(JournalEntry journalEntry, String username){
        try{
            UsersEntry user = userSerive.findByUsername(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntryList().add(saved);
            userSerive.SaveUser(user);
        }catch (Exception e){
            System.out.print(e);
            throw new RuntimeException("An error occured while saving the entry.",e);
        }
    }

    public void SaveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id,String username){
        boolean removed = false;
        try {
            UsersEntry user = userSerive.findByUsername(username);
            removed = user.getJournalEntryList().removeIf(x -> x.getId().equals(id));
            if(removed){
                userSerive.SaveUser(user);
                journalEntryRepository.deleteById(id);
            }
        }catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occuered");
        }
        return removed;
    }

}
