package com.kirthisamson.votingsystem.Services;

import com.kirthisamson.votingsystem.dao.User;

/**
 * The User Service. A Service user to create and manage users for the application
 *
 * @author Kirthi Samson Chilkuri
 */
public interface UserService {
    /**
     * Gets all the users of the system
     * @return list of User objects
     */
    Iterable<User> getAllUsers();

    /**
     * Add a user to the system using a User object
     * @param user
     * @return a User object
     */
    User addUser(User user);

    /**
     * Add a user to the system using their name and email
     * @param username
     * @param email
     * @return a User object
     */
    User addUser(String username, String email);

    /**
     * Get a user by their name
     * @param name
     * @return a User object
     */
    User getUserByName(String name);

    /**
     * Get a user by their ID
     * @param id
     * @return a User Object
     */
    User getUser(int id);

    /**
     * Get a user by their email
     * @param email
     * @return a User object
     */
    User getUserByEmail(String email);
}
