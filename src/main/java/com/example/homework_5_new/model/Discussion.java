package com.example.homework_5_new.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Discussion implements Serializable {
    private int discussionId;
    private String topic;
    private String body;
    private String creator;
    private Date created;
    private List<Reply> responses;

    public Discussion() {
        this.responses = new ArrayList<>();
    }
    public Discussion(int discussionId, String topic, String body, String creator) {
        this.discussionId = discussionId;
        this.topic = topic;
        this.body = body;
        this.creator = creator;
        this.created = new Date();
        this.responses = new ArrayList<>();
    }

    public int getId() { return discussionId; }
    public void setId(int discussionId) { this.discussionId = discussionId; }
    public String getTitle() { return topic; }
    public void setTitle(String topic) { this.topic = topic; }
    public String getContent() { return body; }
    public void setContent(String body) { this.body = body; }
    public String getAuthor() { return creator; }
    public void setAuthor(String creator) { this.creator = creator; }
    public Date getCreateTime() { return created; }
    public void setCreateTime(Date created) { this.created = created; }
    public List<Reply> getReplies() { return responses; }
    public void setReplies(List<Reply> responses) { this.responses = responses; }
}