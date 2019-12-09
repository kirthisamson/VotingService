package com.kirthisamson.votingsystem.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionStats {
    int questionId;
    String questionText;
    int yesCount;
    int noCount;
    int totalVoteCount;
    String yesPercentage;
    String noPercentage;
}
