# Simple Voting System

A coding exercise for the University of Colorado OIT Software Engineer / Software Analyst position

## The original spec is as follows

The task is to create a simple voting service using Spring Boot. The main objective of this service is to allow a diverse group of voters to vote on given yes/no style questions.

## Requirements

- The backend database store is expected to be an SQLite database.
- The application itself is expected to be written in Spring Boot.
- There is no UI component to this exercise.
- At your discretion, you may supply up to five seed questions you wish to run the actual application, so long as they are in the form of a Yes/No question. One example would be, "Do you currently own a bicycle?"
- Voters "self-identify" as they vote; as part of their voting, they submit their name to the service.
- Only one voter can vote once on a particular question. Voter A should not be able to submit multiple repsonses to Question A.
- Concurrent votes on a given question are expected to be handled. Voters A and B should be able to vote on Question A and not have their votes cancel out.
- An endpoint should be exposed to aggregate statistics and facts about the number of votes for a given question. An example would be how many voters voted Yes for Question A versus how many people voted No for Question A, along with a percentage breakdown (formatted as xx%).
- Because these are votes, and voting is a sensitive process, the aggregate endpoint must not include any information about which voters voted for what, nor should that information be logged out anywhere.

## Deliverables

- Full source code with the ability to generate a working Spring Boot JAR
- The SQLite database as a .db extension file
- A JAR containing Javadoc of the web service
- A JAR containing the full source code of the web service

## Implementation

The work flow this implementation follows is as follows

1. The User/Voter registers themselves using their name and email. This will generate a userId.
2. A list of unvoted questions for a User/Voter can be obtained using the UserId. Each Question will be associated with a QuestionId
3. A vote can be cast using the UserId, the QuestionId and a boolean Yes/No

An Aggregate Stats endpoint is exposed to get anonymized metrics about a question

## What is included in the zip

As mentioned in the requirements the zip file will contail

1. The Voting System Gradle Project
2. The Database file
3. A JAR of the Javadoc
4. A JAR of the Sources

## Special mentions

### [Project Lombok](https://projectlombok.org/)

Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java.
Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.

In this project we use annotations like @Data, @AllArgsConstructor, @Builder, @NoArgsConstructor,
@AllArgsConstructor to reduce boilerplate and add functionality

Please visit [link](https://projectlombok.org/) for more information
