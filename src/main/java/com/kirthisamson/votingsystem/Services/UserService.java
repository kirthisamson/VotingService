package com.kirthisamson.votingsystem.Services;

import com.kirthisamson.votingsystem.dao.User;

public interface UserService {
    Iterable<User> getAllUsers();
    void addUser(User user);
    void addUser(String username, String email);

    User getUserByName(String name);
    User getUser(int id);
    User getUserByEmail(String email);
}
