package com.kirthisamson.votingsystem.repositories;

import com.kirthisamson.votingsystem.dao.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
}
