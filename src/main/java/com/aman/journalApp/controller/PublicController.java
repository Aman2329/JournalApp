package com.aman.journalApp.controller;

import com.aman.journalApp.entity.User;
import com.aman.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String check(){
        return "ok";
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        userService.saveEntry(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
