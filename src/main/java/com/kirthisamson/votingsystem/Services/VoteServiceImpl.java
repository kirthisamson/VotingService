package com.kirthisamson.votingsystem.Services;

import com.kirthisamson.votingsystem.dao.Vote;
import com.kirthisamson.votingsystem.repositories.VoteRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.NoSuchElementException;

/**
 * {@inheritDoc}
 */
@Service
public class VoteServiceImpl implements VoteService {

  VoteRepository voteRepository;
  UserService userService;

  @Autowired
  public VoteServiceImpl(VoteRepository voteRepository,
                         UserService userService) {

    Assert.notNull(voteRepository, "Vote Repository cannot be null");
    Assert.notNull(userService, "Question Service cannot be null");

    this.voteRepository = voteRepository;
    this.userService = userService;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterable<Vote> getAllVotes() {
    return voteRepository.findAll();
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public int getYesCountForQuestion(int questionId) {
    return voteRepository.getYesCountForQuestion(questionId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getNoCountForQuestion(int questionId) {
    return voteRepository.getNoCountForQuestion(questionId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getVoteCountForQuestion(int questionId) {
    return voteRepository.getVoteCountForQuestion(questionId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void castVote(int userId,
                       int questionId,
                       boolean vote) throws Exception {

    if(userService.getUser(userId) == null) throw new NoSuchElementException("The id used does not correspond to a user");
    if(getCastVote(userId, questionId) != null) { throw new IllegalStateException(); }

    Vote voteToCast = Vote.builder()
            .userId(userId)
            .questionId(questionId)
            .vote(vote)
            .build();

    voteRepository.save(voteToCast);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterable<Vote> getCastVotesForUser(int userId) {
    return voteRepository.findByUserId(userId);
  }

  /**
   * {@inheritDoc}
   */
  private Vote getCastVote(int userId,
                           int questionId) {
    return voteRepository.findByUserIdAndQuestionId(userId, questionId);
  }
}
