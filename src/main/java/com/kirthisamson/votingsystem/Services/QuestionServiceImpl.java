package com.kirthisamson.votingsystem.Services;

import com.kirthisamson.votingsystem.dao.Question;
import com.kirthisamson.votingsystem.dao.Vote;
import com.kirthisamson.votingsystem.repositories.QuestionRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.kirthisamson.votingsystem.Util.StreamUtil.not;

/**
 * {@inheritDoc}
 */
@Service
public class QuestionServiceImpl implements QuestionService {

  QuestionRepository questionRepository;
  VoteService voteService;

  @Autowired
  public QuestionServiceImpl(QuestionRepository questionRepository,
                             VoteService voteService) {

    Assert.notNull(questionRepository, "Question Repository cannot be null");
    Assert.notNull(voteService, "Vote Service cannot be null");

    this.questionRepository = questionRepository;
    this.voteService = voteService;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterable<Question> getAllQuestions() {
    return questionRepository.findAll();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addQuestion(@NonNull String questionText) {
    Question question = Question.builder()
            .questionText(questionText)
            .build();
    questionRepository.save(question);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void seedQuestions() {
    Iterable<Question> questions = questionRepository.findAll();

    long questionsCount = StreamSupport.stream(questions.spliterator(), false).count();

    if(questionsCount <= 0) {
      addQuestion("Do you like the Cybertruck?");
      addQuestion("Is the Sky blue?");
      addQuestion("Do you think electric cars are the future?");
      addQuestion("Is the Earth flat?");
      addQuestion("Do you wanna relocate to Mars?");
    }

    questions = questionRepository.findAll();

    for(Question question: questions) {
      System.out.println(question.getQuestionText());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Question> getQuestionsForUser(int userId) {
    Iterable<Question> questions = questionRepository.findAll();

    Iterable<Vote> userVotes =  voteService.getCastVotesForUser(userId);


    if(userVotes == null){
      return StreamSupport.stream(questions.spliterator(), false).collect(Collectors.toList());
    }

    long userVoteCount = StreamSupport.stream(userVotes.spliterator(), false).count();
    if(userVoteCount == 0) {
      return StreamSupport.stream(questions.spliterator(), false).collect(Collectors.toList());
    }

    List<Integer> questionIdsAlreadyVotedByUser = StreamSupport.stream(userVotes.spliterator(), false)
                                                                .map(Vote::getQuestionId)
                                                                .collect(Collectors.toList());

    return StreamSupport.stream(questions.spliterator(), false)
                        .filter(not(q -> questionIdsAlreadyVotedByUser.contains(q.getId())))
                        .collect(Collectors.toList());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Question getQuestion(int questionId) {
    return questionRepository.findById(questionId).get();
  }
}
