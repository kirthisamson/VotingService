package com.kirthisamson.votingsystem.Services;

import com.kirthisamson.votingsystem.dao.Question;
import com.kirthisamson.votingsystem.dao.Vote;
import com.kirthisamson.votingsystem.repositories.QuestionRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.kirthisamson.votingsystem.Util.StreamUtil.not;

@Service
public class QuestionServiceImpl implements QuestionService {

  @Autowired
  QuestionRepository questionRepository;

  @Autowired
  VoteService voteService;

  @Override
  public Iterable<Question> getAllQuestions() {
    return questionRepository.findAll();
  }

  @Override
  public void addQuestion(@NonNull String questionText) {
    Question question = Question.builder()
            .questionText(questionText)
            .build();
    questionRepository.save(question);
  }

  @Override
  public void seedQuestions() {
    Iterable<Question> questions = questionRepository.findAll();

    long questionsCount = StreamSupport.stream(questions.spliterator(), false).count();

    if(questionsCount <= 0) {
      addQuestion("Do you like the Cybertruck?");
      addQuestion("Is the Sky blue?");
    }

    questions = questionRepository.findAll();

    for(Question question: questions) {
      System.out.println(question.getQuestionText());
    }
  }

  @Override
  public List<Question> getQuestionsForUser(@NonNull int userId) {
    Iterable<Question> questions = questionRepository.findAll();

    Iterable<Vote> userVotes =  voteService.getCastVotesForUser(userId);
    long userVoteCount = StreamSupport.stream(userVotes.spliterator(), false).count();

    if(userVotes == null || userVoteCount == 0){
      return StreamSupport.stream(questions.spliterator(), false).collect(Collectors.toList());
    }

    List<Integer> questionIdsAlreadyVotedByUser = StreamSupport.stream(userVotes.spliterator(), false)
                                                                .map(Vote::getQuestionId)
                                                                .collect(Collectors.toList());

    return StreamSupport.stream(questions.spliterator(), false)
                        .filter(not(q -> questionIdsAlreadyVotedByUser.contains(q.getId())))
                        .collect(Collectors.toList());
  }

  @Override
  public Question getQuestion(int questionId) {
    return questionRepository.findById(questionId).get();
  }
}
