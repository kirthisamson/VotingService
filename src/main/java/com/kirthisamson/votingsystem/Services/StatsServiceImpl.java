package com.kirthisamson.votingsystem.Services;

import com.kirthisamson.votingsystem.dao.Question;
import com.kirthisamson.votingsystem.models.QuestionStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * {@inheritDoc}
 */
@Service
public class StatsServiceImpl implements StatsService {

  VoteService voteService;
  QuestionService questionService;

  @Autowired
  public StatsServiceImpl(VoteService voteService,
                          QuestionService questionService) {

    Assert.notNull(voteService, "Vote Service cannot be null");
    Assert.notNull(questionService, "Question Service cannot be null");

    this.voteService = voteService;
    this.questionService = questionService;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public QuestionStats getStatsForQuestion(int questionId) {
    Question question = questionService.getQuestion(questionId);

    int yesCount = voteService.getYesCountForQuestion(questionId);
    int noCount = voteService.getNoCountForQuestion(questionId);
    int voteCount = voteService.getVoteCountForQuestion(questionId);

    int yesPercentage = yesCount * 100/voteCount;
    int noPercentage = noCount * 100/voteCount;

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
