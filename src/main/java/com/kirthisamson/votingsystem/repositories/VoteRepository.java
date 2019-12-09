package com.kirthisamson.votingsystem.repositories;

import com.kirthisamson.votingsystem.dao.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("voteRepository")
public interface VoteRepository extends CrudRepository<Vote, Integer> {

//    @Query("SELECT v FROM Vote v WHERE u.userId = ?1 and u.questionId = ?2")
    Vote findByUserIdAndQuestionId(int userId, int questionId);

    Iterable<Vote> findByUserId(int userId);

    @Query("Select Count(*) from Vote v WHERE v.questionId = ?1 and v.vote=1")
    int getYesCountForQuestion(int questionId);

    @Query("Select Count(*) from Vote v WHERE v.questionId = ?1 and v.vote=0")
    int getNoCountForQuestion(int questionId);

    @Query("Select Count(*) from Vote v WHERE v.questionId = ?1")
    int getVoteCountForQuestion(int questionId);
}
