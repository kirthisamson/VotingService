package com.kirthisamson.votingsystem.Services;

import com.kirthisamson.votingsystem.dao.Vote;

public interface VoteService {
    Iterable<Vote> getAllVotes();

    void castVote(int userId, int questionId, boolean vote) throws Exception;
    Iterable<Vote> getCastVotesForUser(int userId);

    int getYesCountForQuestion(int questionId);
    int getNoCountForQuestion(int questionId);
    int getVoteCountForQuestion(int questionId);
}
