package com.kirthisamson.votingsystem.Services;

import com.kirthisamson.votingsystem.models.QuestionStats;

public interface StatsService {
    QuestionStats getStatsForQuestion(int questionId);
}
