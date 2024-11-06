package com.aman.journalApp.controller;

import com.aman.journalApp.entity.JournalEntry;
import com.aman.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }

    @GetMapping("id/{id}")
    public JournalEntry getJournalEntryById(@PathVariable("id") ObjectId id){
        return journalEntryService.findById(id).orElse(null);
    }

    @DeleteMapping("id/{id}")
    public String deleteJournalEntryById(@PathVariable("id") ObjectId id){
        journalEntryService.deleteById(id);
        return "Deleted Sucessful";
    }

    @PutMapping("id/{id}")
    public JournalEntry updateJournalById(@PathVariable("id") ObjectId id, @RequestBody JournalEntry newEntry) {
        JournalEntry old = journalEntryService.findById(id).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent(): old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;
    }
}
