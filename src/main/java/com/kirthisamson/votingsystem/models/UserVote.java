package com.kirthisamson.votingsystem.models;

import lombok.Data;

@Data
public class UserVote {
    int userId;
    int questionId;
    boolean vote;
}
