package com.spring.instagram.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCommentModel {
    private int postid;
    private String body;

    public PostCommentModel(int postid, String body) {
        this.postid = postid;
        this.body = body;
    }
}
