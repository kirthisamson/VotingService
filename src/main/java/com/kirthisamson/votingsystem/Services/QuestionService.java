package com.kirthisamson.votingsystem.Services;

import com.kirthisamson.votingsystem.dao.Question;

/**
 * The Question Service. This service is used to add and manage questions
 *
 * @author Kirthi Samson Chilkuri
 */
public interface QuestionService {

    /**
     * Get all questions for the voting system
     * @return a list of Question objects
     */
    Iterable<Question> getAllQuestions();

    /**
     * Get a question by ID
     * @param questionId
     * @return a Question object
     */
    Question getQuestion(int questionId);

    /**
     * Add a question to the voting system
     * @param questionText
     */
    void addQuestion(String questionText);

    /**
     * Get questions for a particular user. This list wont contain the questions that the user has already voted on
     * @param userId
     * @return a list of Question objects
     */
    Iterable<Question> getQuestionsForUser(int userId);

    /**
     * A method to seed questions
     */
    void seedQuestions();
}
