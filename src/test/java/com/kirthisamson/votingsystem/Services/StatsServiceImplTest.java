package com.kirthisamson.votingsystem.Services;

import com.kirthisamson.votingsystem.dao.Question;
import com.kirthisamson.votingsystem.models.QuestionStats;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class StatsServiceImplTest {

  @Mock
  QuestionService questionService;

  @Mock
  VoteService voteService;

  @InjectMocks
  StatsServiceImpl statsService;

  Question question1;

  @BeforeEach
  public void init() {

    statsService = new StatsServiceImpl(voteService, questionService);

    question1 = Question.builder()
                        .questionText("Question 1 for test")
                        .id(1)
                        .build();

    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void getStatsForQuestionWhenEqualVotes() {

    Mockito.when(questionService.getQuestion(1)).thenReturn(question1);

    Mockito.when(voteService.getYesCountForQuestion(1)).thenReturn(10);
    Mockito.when(voteService.getNoCountForQuestion(1)).thenReturn(10);
    Mockito.when(voteService.getVoteCountForQuestion(1)).thenReturn(20);

    QuestionStats statsWhenVotesAreEqual = statsService.getStatsForQuestion(1);

    Assert.assertEquals("50%", statsWhenVotesAreEqual.getYesPercentage());
    Assert.assertEquals("50%", statsWhenVotesAreEqual.getNoPercentage());

  }

  @Test
  public void getStatsForQuestionWhenOnlyYesVotes() {

    Mockito.when(questionService.getQuestion(1)).thenReturn(question1);

    Mockito.when(voteService.getYesCountForQuestion(1)).thenReturn(20);
    Mockito.when(voteService.getNoCountForQuestion(1)).thenReturn(0);
    Mockito.when(voteService.getVoteCountForQuestion(1)).thenReturn(20);

    QuestionStats statsWhenVotesAreEqual = statsService.getStatsForQuestion(1);

    Assert.assertEquals("100%", statsWhenVotesAreEqual.getYesPercentage());
    Assert.assertEquals("0%", statsWhenVotesAreEqual.getNoPercentage());

  }

  @Test
  public void getStatsForQuestionWhenOnlyNoVotes() {

    Mockito.when(questionService.getQuestion(1)).thenReturn(question1);

    Mockito.when(voteService.getYesCountForQuestion(1)).thenReturn(0);
    Mockito.when(voteService.getNoCountForQuestion(1)).thenReturn(20);
    Mockito.when(voteService.getVoteCountForQuestion(1)).thenReturn(20);

    QuestionStats statsWhenVotesAreEqual = statsService.getStatsForQuestion(1);

    Assert.assertEquals("0%", statsWhenVotesAreEqual.getYesPercentage());
    Assert.assertEquals("100%", statsWhenVotesAreEqual.getNoPercentage());

  }
}