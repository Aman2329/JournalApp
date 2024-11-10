package com.aman.journalApp.controller;


import com.aman.journalApp.entity.User;
import com.aman.journalApp.repository.UserRepository;
import com.aman.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

//    @GetMapping
//    public List<User> getAllUser(){
//        return userService.getAll();
//    }
// we make this for admin , not required in userController

//    @PostMapping
//    public ResponseEntity<User> createUser(@RequestBody User user){
//        userService.saveEntry(user);
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
//    } // we move this to the public controller

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
//        if(userInDb != null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
//        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
