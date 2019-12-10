package com.kirthisamson.votingsystem.Services;

import com.kirthisamson.votingsystem.dao.Vote;

/**
 * The VoteService. A Service for casting and getting information regarding the votes
 *
 * @author Kirthi Samson Chilkuri
 */
public interface VoteService {
    /**
     * Get All Votes of the system
     * @return a list of Vote objects
     */
    Iterable<Vote> getAllVotes();

    /**
     * Cast a Vote for a particular question.
     * The existance of a user is mandatory to cast a vote.
     * A duplicate vote for the same question by the same user is prohibited
     * @param userId
     * @param questionId
     * @param vote
     * @throws Exception
     */
    void castVote(int userId, int questionId, boolean vote) throws Exception;

    /**
     * Get votes cast by a particular user
     * @param userId
     * @return a list of Vote objects
     */
    Iterable<Vote> getCastVotesForUser(int userId);

    /**
     * Get the count of all the 'Yes' votes for a question
     * @param questionId
     * @return Count of 'Yes' votes for a given question
     */
    int getYesCountForQuestion(int questionId);

    /**
     * Get the count of all the 'No' votes for a question
     * @param questionId
     * @return Count of 'No' votes for a given question
     */
    int getNoCountForQuestion(int questionId);

    /**
     * Get the count of all the votes for a question
     * @param questionId
     * @return Count of votes for a given question
     */
    int getVoteCountForQuestion(int questionId);
}
