package com.kirthisamson.votingsystem.Services;

import com.kirthisamson.votingsystem.dao.Question;
import com.kirthisamson.votingsystem.models.QuestionStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsService {

  @Autowired
  VoteService voteService;

  @Autowired
  QuestionService questionService;


  /**
   * Generates Voting Stats for a question.
   * @param questionId
   * @return an object of QuestionStats which contains the Question Id, the Question Text,
   * Count for all the 'Yes' votes, count for 'No' votes, Total votes for that question, and Yes and No percentages
   */

  @Override
  public QuestionStats getStatsForQuestion(int questionId) {
    Question question = questionService.getQuestion(questionId);

    int yesCount = voteService.getYesCountForQuestion(questionId);
    int noCount = voteService.getNoCountForQuestion(questionId);
    int voteCount = voteService.getVoteCountForQuestion(questionId);

    int yesPercentage = yesCount/voteCount * 100;
    int noPercentage = noCount/voteCount * 100;

    String yesPercentageText = Integer.toString(yesPercentage) + "%";
    String noPercentageText = Integer.toString(noPercentage) + "%";

    return QuestionStats.builder()
            .questionId(questionId)
            .questionText(question.getQuestionText())
            .yesCount(yesCount)
            .noCount(noCount)
            .totalVoteCount(voteCount)
            .yesPercentage(yesPercentageText)
            .noPercentage(noPercentageText)
            .build();
  }
}
