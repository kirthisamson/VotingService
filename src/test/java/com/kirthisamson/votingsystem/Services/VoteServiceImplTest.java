package com.kirthisamson.votingsystem.Services;

import com.kirthisamson.votingsystem.dao.User;
import com.kirthisamson.votingsystem.dao.Vote;
import com.kirthisamson.votingsystem.repositories.VoteRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VoteServiceImplTest {

  @Mock
  VoteRepository voteRepository;

  @Mock
  UserService userService;

  VoteServiceImpl voteServiceImpl;

  Vote vote1ByUser1, vote2ByUser1;

  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks((this));
    voteServiceImpl = new VoteServiceImpl(voteRepository, userService);

    vote1ByUser1 = Vote.builder()
            .id(1)
            .userId(1)
            .questionId(1)
            .vote(true)
            .build();

    vote2ByUser1 = Vote.builder()
            .id(1)
            .userId(1)
            .questionId(2)
            .vote(false)
            .build();

  }

  @Test
  public void getAllVotes_Test() {
    List<Vote> allVotes = new ArrayList<Vote>();
    allVotes.add(vote1ByUser1);
    allVotes.add(vote2ByUser1);

    Mockito.when(voteRepository.findAll()).thenReturn(allVotes);
    Assert.assertEquals(allVotes, voteServiceImpl.getAllVotes());
  }

  @Test
  public void getYesCountForQuestion_Test() {
    Mockito.when(voteRepository.getYesCountForQuestion(1)).thenReturn(20);
    Assert.assertEquals(20, voteServiceImpl.getYesCountForQuestion(1));
  }

  @Test
  public void getNoCountForQuestion_Test() {
    Mockito.when(voteRepository.getNoCountForQuestion(1)).thenReturn(20);
    Assert.assertEquals(20, voteServiceImpl.getNoCountForQuestion(1));
  }

  @Test
  public void getVoteCountForQuestion_Test() {
    Mockito.when(voteRepository.getVoteCountForQuestion(1)).thenReturn(20);
    Assert.assertEquals(20, voteServiceImpl.getVoteCountForQuestion(1));
  }

  @Test
  public void castVote_Test() throws Exception {

    User user1 = User.builder()
            .id(1)
            .name("user1")
            .email("user1@nonamecompany.com")
            .build();

    Mockito.when(userService.getUser(1)).thenReturn(user1);
    Mockito.when(voteRepository.findByUserIdAndQuestionId(1,2)).thenReturn(null);

    Vote voteToCast = Vote.builder()
            .userId(1)
            .questionId(2)
            .vote(true)
            .build();
    voteServiceImpl.castVote(1,2, true);
    Mockito.verify(voteRepository).save(voteToCast);
  }

  @Test
  public void castVoteNoUserThrowsException_Test() throws Exception {

    assertThrows(NoSuchElementException.class, () -> {

      Mockito.when(userService.getUser(1)).thenReturn(null);
      voteServiceImpl.castVote(1,2, true);

    });

  }

  @Test
  public void castVoteAlreadyCastVoteThrowsIllegalStateException_Test() throws Exception {

    assertThrows(IllegalStateException.class, () -> {
      User user1 = User.builder()
              .id(1)
              .name("user1")
              .email("user1@nonamecompany.com")
              .build();

      Vote alreadyCastVote = Vote.builder()
              .userId(1)
              .questionId(2)
              .vote(true)
              .build();

      Mockito.when(userService.getUser(1)).thenReturn(user1);
      Mockito.when(voteRepository.findByUserIdAndQuestionId(1,2)).thenReturn(alreadyCastVote);

      voteServiceImpl.castVote(1,2, true);
    });
  }
}