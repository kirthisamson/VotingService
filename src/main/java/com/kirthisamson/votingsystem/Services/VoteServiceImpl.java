package com.kirthisamson.votingsystem.Services;

import com.kirthisamson.votingsystem.dao.Vote;
import com.kirthisamson.votingsystem.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class VoteServiceImpl implements VoteService {

  @Autowired
  VoteRepository voteRepository;

  @Autowired
  UserService userService;

  @Override
  public Iterable<Vote> getAllVotes() {
    return voteRepository.findAll();
  }

  @Override
  public int getYesCountForQuestion(int questionId) {
    return voteRepository.getYesCountForQuestion(questionId);
  }

  @Override
  public int getNoCountForQuestion(int questionId) {
    return voteRepository.getNoCountForQuestion(questionId);
  }

  @Override
  public int getVoteCountForQuestion(int questionId) {
    return voteRepository.getNoCountForQuestion(questionId);
  }

  @Override
  public void castVote(int userId, int questionId, boolean vote) throws Exception {

    if(userService.getUser(userId) == null) throw new NoSuchElementException("The id used does not correspond to a user");
    if(getCastVote(userId, questionId) != null) { throw new IllegalArgumentException(); }

    Vote voteToCast = Vote.builder()
            .userId(userId)
            .questionId(questionId)
            .vote(vote)
            .build();

    voteRepository.save(voteToCast);
  }

  @Override
  public Iterable<Vote> getCastVotesForUser(int userId) {
    return voteRepository.findByUserId(userId);
  }

  private Vote getCastVote(int userId, int questionId) {
    return voteRepository.findByUserIdAndQuestionId(userId, questionId);
  }
}
