package JournalApp.MyFirstApp.controller;

import JournalApp.MyFirstApp.Entry.JournalEntry;
import JournalApp.MyFirstApp.Entry.UsersEntry;
import JournalApp.MyFirstApp.Service.JournalEntryService;
import JournalApp.MyFirstApp.Service.UserService;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {


    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesofUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UsersEntry user = userService.findByUsername(username);
        List<JournalEntry> all = user.getJournalEntryList();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        // ✅ Validate BEFORE doing anything
        if (myEntry.getTitle() == null || myEntry.getContent() == null ||
                myEntry.getTitle().isBlank() || myEntry.getContent().isBlank()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // return null instead of myEntry
        }

        try {
            journalEntryService.SaveEntry(myEntry, username);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // don't return possibly saved object
        }
    }



    @GetMapping("id/{MyID}")
    public ResponseEntity<JournalEntry> getJournalEntry(@PathVariable ObjectId MyID){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UsersEntry user = userService.findByUsername(username);
        List<JournalEntry> collect = user.getJournalEntryList().stream().filter(x-> x.getId().equals(MyID)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.findById(MyID);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("ID/{MyID}")
    public ResponseEntity<?> DeleteJournalEntry(@PathVariable ObjectId MyID){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean removed = journalEntryService.deleteById(MyID,username);
        if(removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> putJournalEntry(@PathVariable ObjectId id,
                                             @RequestBody JournalEntry myEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UsersEntry user = userService.findByUsername(username);
        List<JournalEntry> collect = user.getJournalEntryList().stream().filter(x-> x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
            if(journalEntry.isPresent()){
                JournalEntry old = journalEntry.get();
                old.setTitle(myEntry.getTitle() != null && !myEntry.getContent().equals("") ? myEntry.getTitle() : old.getTitle());
                old.setContent(myEntry.getContent() != null && !myEntry.getContent().equals("") ? myEntry.getContent() : old.getContent());
                journalEntryService.SaveEntry(old);
                return new ResponseEntity<>(old,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
