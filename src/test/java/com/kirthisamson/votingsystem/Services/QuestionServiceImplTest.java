package com.kirthisamson.votingsystem.Services;

import com.kirthisamson.votingsystem.dao.Question;
import com.kirthisamson.votingsystem.dao.Vote;
import com.kirthisamson.votingsystem.repositories.QuestionRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

  @Mock
  QuestionRepository questionRepository;

  @Mock
  VoteService voteService;

  @InjectMocks
  QuestionServiceImpl questionService;

  Question question1;

  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks(this);
    questionService = new QuestionServiceImpl(questionRepository, voteService);

    question1 = Question.builder()
            .id(1)
            .questionText("Question 1")
            .build();
  }

  @Test
  public void addQuestion_Test() {
    Question testQuestion = Question.builder()
            .questionText("add question test")
            .build();

    questionService.addQuestion("add question test");
    Mockito.verify(questionRepository).save(testQuestion);
  }

  @Test
  public void getQuestionsForUserForTheFirstTime_Test() {

    Question question2 = Question.builder()
            .id(2)
            .questionText("Question 2")
            .build();

    List<Question> questions = new ArrayList<Question>();
    questions.add(question1);
    questions.add(question2);

    Mockito.when(questionRepository.findAll()).thenReturn(questions);
    List emptyList = new ArrayList<Vote>();
    Mockito.when(voteService.getCastVotesForUser(1)).thenReturn(emptyList);

    List<Question> questionsForUser1 = questionService.getQuestionsForUser(1);
    Assert.assertEquals(questions, questionsForUser1);
  }

  @Test
  public void getOnlyUnvotedQuestionsForUser_Test() {

    Question question2 = Question.builder()
            .id(2)
            .questionText("Question 2")
            .build();

    Vote vote1 = Vote.builder()
            .id(1)
            .questionId(1)
            .userId(1)
            .vote(true)
            .build();

    List<Question> questions = new ArrayList<Question>();
    questions.add(question1);
    questions.add(question2);

    Mockito.when(questionRepository.findAll()).thenReturn(questions);

    List someVoted = new ArrayList<Vote>();
      someVoted.add(vote1);

    Mockito.when(voteService.getCastVotesForUser(1)).thenReturn(someVoted);

    List<Question> questionsForUser1 = questionService.getQuestionsForUser(1);
    Assert.assertNotEquals(questions, questionsForUser1);
    Assert.assertEquals(1, questionsForUser1.size());
    Assert.assertEquals("Question 2", questionsForUser1.get(0).getQuestionText());
  }

  @Test
  public void getQuestionById_Test() {
    Mockito.when(questionRepository.findById(1)).thenReturn(Optional.of(question1));
    Assert.assertEquals(question1, questionService.getQuestion(1));
  }

}