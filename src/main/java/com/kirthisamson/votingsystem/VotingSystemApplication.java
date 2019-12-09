package com.kirthisamson.votingsystem;

import com.kirthisamson.votingsystem.Services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class VotingSystemApplication implements CommandLineRunner {

	@Autowired
	QuestionService questionService;

	public static void main(String[] args) {
		SpringApplication.run(VotingSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Started");

		questionService.seedQuestions();
	}
}
