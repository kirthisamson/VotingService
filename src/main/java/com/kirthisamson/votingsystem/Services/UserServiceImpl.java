package com.kirthisamson.votingsystem.Services;

import com.kirthisamson.votingsystem.dao.User;
import com.kirthisamson.votingsystem.exception.UserAlreadyExistsException;
import com.kirthisamson.votingsystem.repositories.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
      return userRepository.findByEmail(email);
    }

    @Override
    public void addUser(@NonNull User user) {
        userRepository.save(user);
    }

    @Override
    public void addUser(@NonNull String username,
                        @NonNull String email) {

        if(getUserByEmail(email) != null) throw new UserAlreadyExistsException("User's email is already in use. User already exists");

        User user = User.builder()
                        .name(username)
                        .email(email)
                        .build();
        userRepository.save(user);
    }

    @Override
    public User getUserByName(@NonNull String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User getUser(int id) {
        return userRepository.findById(id).get();
    }
}
