package com.kirthisamson.votingsystem.controllers;

import com.kirthisamson.votingsystem.Services.UserService;
import com.kirthisamson.votingsystem.dao.User;
import com.kirthisamson.votingsystem.models.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(params="name")
    public User getUserByName(@RequestParam("name") String name) {
        return userService.getUserByName(name);
    }

    @GetMapping(value= "/{id}")
    public User getUser(@PathVariable("id") int id) {
        return userService.getUser(id);
    }

    @PostMapping
    public void addUser(@RequestBody UserRegistration userRegistration) {
        userService.addUser(userRegistration.getName(), userRegistration.getEmail());
    }
}
