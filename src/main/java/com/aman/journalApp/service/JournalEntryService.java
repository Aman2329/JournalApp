package com.aman.journalApp.service;

import com.aman.journalApp.entity.JournalEntry;
import com.aman.journalApp.entity.User;
import com.aman.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved); //because journalEntries is a list
//        user.setUserName(null); -- used for debug and implement Transactional
            userService.saveUser(user);// dont use saveNewUser
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An Error occur while saving the entry." ,e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){
       journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){

        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){

        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveUser(user); // dont use saveNewUser
                journalEntryRepository.deleteById(id);
            }
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An Error occur while deleting the entry." ,e);
        }
        return removed;
    }

}
