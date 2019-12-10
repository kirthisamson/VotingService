package com.kirthisamson.votingsystem.controllers;

import com.kirthisamson.votingsystem.Services.QuestionService;
import com.kirthisamson.votingsystem.dao.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    /**
     * An endpoint for getting a list of all questions
     * @return list of questions
     */
    @GetMapping(value="")
    Iterable<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    /**
     * An endpoint for getting a list of questions for a user
     * @param userId
     * @return list of questions
     */
    @GetMapping(params="userId")
    Iterable<Question> getQuestionsForUser(@RequestParam("userId") int userId) {
        return questionService.getQuestionsForUser(userId);
    }
}