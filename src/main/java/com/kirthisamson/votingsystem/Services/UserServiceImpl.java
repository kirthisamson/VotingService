package com.kirthisamson.votingsystem.Services;

import com.kirthisamson.votingsystem.dao.User;
import com.kirthisamson.votingsystem.exception.UserAlreadyExistsException;
import com.kirthisamson.votingsystem.repositories.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {

        Assert.notNull(userRepository, "User Repository cannot be null");

        this.userRepository = userRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserByEmail(@NonNull String email) {
      return userRepository.findByEmail(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(int id) {
        return userRepository.findById(id).get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User addUser(@NonNull User user) {
        return userRepository.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User addUser(@NonNull String username,
                        @NonNull String email) {

        if(getUserByEmail(email) != null) throw new UserAlreadyExistsException("User's email is already in use. User already exists");

        User user = User.builder()
                        .name(username)
                        .email(email)
                        .build();
        return userRepository.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserByName(@NonNull String name) {
        return userRepository.findByName(name);
    }
}
