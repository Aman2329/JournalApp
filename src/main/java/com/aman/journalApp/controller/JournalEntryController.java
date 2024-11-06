package com.aman.journalApp.controller;

import com.aman.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    public HashMap<String, JournalEntry> journalEntries = new HashMap<>();

    @PostMapping
    public String createJournal(@RequestBody JournalEntry myEntry){
        journalEntries.put(myEntry.getId(), myEntry);
        return "Created Sucessfully";
    }

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @GetMapping("{id}")
    public JournalEntry getById(@PathVariable("id") String id){
        return journalEntries.get(id);
    }

    @PutMapping("{id}")
    public String updateById(@PathVariable("id") String id, @RequestBody JournalEntry updateEntry) {
        if (journalEntries.containsKey(id)) {
            updateEntry.setId(id); // Ensure the entry's ID matches the URL parameter
            journalEntries.put(id, updateEntry);
            return "Successfully updated entry with ID " + id;
        } else {
            return "Entry with ID " + id + " not found.";
        }
    }

    @DeleteMapping("{id}")
    public String deleteById(@PathVariable("id") String id){
        journalEntries.remove(id);
        return "Sucessfully Deleted "+id;
    }
}
