package com.kirthisamson.votingsystem.controllers;

import com.kirthisamson.votingsystem.Services.VoteService;
import com.kirthisamson.votingsystem.models.UserVote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("votes")
public class VoteController {

    @Autowired
    VoteService voteService;

    @PostMapping(value="")
    void castVote(@RequestBody UserVote userVote) throws Exception {
        voteService.castVote(userVote.getUserId(), userVote.getQuestionId(), userVote.isVote());
    }
}
