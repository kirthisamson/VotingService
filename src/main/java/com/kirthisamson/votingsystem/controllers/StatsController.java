package com.kirthisamson.votingsystem.controllers;

import com.kirthisamson.votingsystem.Services.StatsService;
import com.kirthisamson.votingsystem.models.QuestionStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "stats")
public class StatsController {

    @Autowired
    StatsService statsService;

    @GetMapping(value = "/{questionId}")
    public QuestionStats getStatsForQuestion(@PathVariable int questionId) {
        return statsService.getStatsForQuestion(questionId);
    }
}
