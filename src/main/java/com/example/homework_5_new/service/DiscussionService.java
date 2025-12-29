package com.example.homework_5_new.service;

import com.example.homework_5_new.model.Discussion;
import com.example.homework_5_new.model.Reply;
import com.example.homework_5_new.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscussionService {
    private final List<Discussion> discussionList = new ArrayList<>();

    public List<Discussion> getAll() { return discussionList; }

    public Discussion getById(int id) {
        return discussionList.stream().filter(d -> d.getId() == id).findFirst().orElse(null);
    }

    public Discussion add(String title, String content, User user) {
        Discussion d = new Discussion(discussionList.size() + 1, title, content, user.getUsername());
        discussionList.add(d);
        return d;
    }

    public void addReply(int discussionId, String content, User user) {
        Discussion d = getById(discussionId);
        if (d == null) return;
        Reply r = new Reply(d.getReplies().size() + 1, content, user.getUsername());
        d.getReplies().add(r);
    }
}