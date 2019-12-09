package com.kirthisamson.votingsystem.Services;

import com.kirthisamson.votingsystem.dao.Question;

public interface QuestionService {
    Iterable<Question> getAllQuestions();
    Question getQuestion(int questionId);
    void addQuestion(String questionText);
    Iterable<Question> getQuestionsForUser(int userId);
    void seedQuestions();
}
