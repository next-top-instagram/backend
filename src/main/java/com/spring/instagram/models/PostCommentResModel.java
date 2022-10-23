package com.spring.instagram.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PostCommentResModel {
    private String email;
    private String body;

    public PostCommentResModel(String email, String body, Date comment_time) {
        this.email = email;
        this.body = body;
        this.comment_time = comment_time;
    }

    private Date comment_time;
}
