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

    /**
     * An endpoint to get all the users of the system
     * @return a list of Users objects
     */
    @GetMapping
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * An endpoint to get a user by name
     * @param name
     * @return a User object
     */
    @GetMapping(params="name")
    public User getUserByName(@RequestParam("name") String name) {
        return userService.getUserByName(name);
    }

    /**
     * An endpoint to get a user by id
     * @param id
     * @return a User object
     */
    @GetMapping(value= "/{id}")
    public User getUser(@PathVariable("id") int id) {
        return userService.getUser(id);
    }

    /**
     * An endpoint to add a user
     * @param userRegistration
     * @return a User object
     */
    @PostMapping
    public User addUser(@RequestBody UserRegistration userRegistration) {
        return userService.addUser(userRegistration.getName(), userRegistration.getEmail());
    }
}
