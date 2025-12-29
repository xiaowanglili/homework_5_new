package com.example.homework_5_new.model;

import java.io.Serializable;
import java.util.Date;

public class Reply implements Serializable {
    private int replyId;
    private String content;
    private String author;
    private Date createTime;

    public Reply() {}
    public Reply(int replyId, String content, String author) {
        this.replyId = replyId;
        this.content = content;
        this.author = author;
        this.createTime = new Date();
    }

    public int getId() { return replyId; }
    public void setId(int replyId) { this.replyId = replyId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}