package com.abhay.moviesManagement.controller;

import com.abhay.moviesManagement.entity.Movie;
import com.abhay.moviesManagement.entity.User;
import com.abhay.moviesManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserService userService;



    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.addUser(user);
        return new ResponseEntity<>(user1,HttpStatus.CREATED);
    }


    @PutMapping("/{userName}"   )
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String userName) {

        User userinDB = userService.findByUserName(userName);

        if (userinDB != null) {
            userinDB.setUserName(user.getUserName());
            userinDB.setPassword(user.getPassword());
            userService.addUser(userinDB);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
