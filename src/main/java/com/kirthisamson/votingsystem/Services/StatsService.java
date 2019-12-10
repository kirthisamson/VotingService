package com.kirthisamson.votingsystem.Services;

import com.kirthisamson.votingsystem.models.QuestionStats;

/**
 * The Stats Service. A Service for getting aggregate statistics regarding the votes
 *
 * @author Kirthi Samson Chilkuri
 */
public interface StatsService {
    /**
     * Generates Voting Stats for a question.
     * @param questionId
     * @return an object of QuestionStats which contains the Question Id, the Question Text,
     * Count for all the 'Yes' votes, count for 'No' votes, Total votes for that question, and Yes and No percentages
     */
    QuestionStats getStatsForQuestion(int questionId);
}
