package com.kirthisamson.votingsystem.Services;

import com.kirthisamson.votingsystem.dao.User;
import com.kirthisamson.votingsystem.repositories.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @Mock
  UserRepository userRepository;

  @InjectMocks
  UserServiceImpl userServiceImpl;

  User user1, user2;

  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks(this);
    userServiceImpl = new UserServiceImpl(userRepository);

    user1 = User.builder()
            .id(1)
            .name("User1")
            .email("user1@nonamecompany.com")
            .build();

    user2 = User.builder()
            .id(2)
            .name("User2")
            .email("user2@nonamecompany.com")
            .build();
  }

  @Test
  public void getAllUsers_Test() {
    List<User> users = new ArrayList<User>();
    users.add(user1);
    users.add(user2);

    Mockito.when(userRepository.findAll()).thenReturn(users);

    Assert.assertEquals(users, userServiceImpl.getAllUsers());
  }

  @Test
  public void getUserById_Test() {
    Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user1));
    Assert.assertEquals(user1, userServiceImpl.getUser(1));
  }

  @Test
  public void getUserByEmail_Test() {
    Mockito.when(userRepository.findByEmail("user1@nonamecompany.com")).thenReturn(user1);
    Assert.assertEquals(user1, userServiceImpl.getUserByEmail("user1@nonamecompany.com"));
  }

  @Test
  public void getUserByName_Test() {
    Mockito.when(userRepository.findByName("user1")).thenReturn(user1);
    Assert.assertEquals(user1, userServiceImpl.getUserByName("user1"));
  }

  @Test
  public void addUser_Test() {
    userServiceImpl.addUser(user1);
    Mockito.verify(userRepository).save(user1);
  }

  @Test
  public void addUserWithNameEmail_Test() {
    User userWithNameEmail = User.builder()
            .name("userWithNameEmail")
            .email("userWithNameEmail@nonamecompany.com")
            .build();

    userServiceImpl.addUser("userWithNameEmail", "userWithNameEmail@nonamecompany.com");
    Mockito.verify(userRepository).save(userWithNameEmail);
  }
}