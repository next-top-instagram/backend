package com.spring.instagram.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "postComment")
public class PostComment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "post_comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    @JsonIgnoreProperties(value = "postCommentList")
    private Post postComment;

    @OneToOne
    @JoinColumn(name = "comment_by_account_id", referencedColumnName = "account_id")
    private Account commentByAccountId;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date commentTime;
    @Column(nullable = false)
    private String body;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Account getCommentByAccountId() {
        return commentByAccountId;
    }

    public void setCommentByAccountId(Account commentByAccountId) {
        this.commentByAccountId = commentByAccountId;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Post getPostComment() {
        return postComment;
    }

    public void setPostComment(Post postComment) {
        this.postComment = postComment;
    }

    @Override
    public String toString() {
        return "PostComment{" +
                "id=" + id +
                ", postComment=" + postComment +
                ", commentByAccountId=" + commentByAccountId +
                ", commentTime=" + commentTime +
                ", body='" + body + '\'' +
                '}';
    }

    public PostComment(Post postComment, Account commentByAccountId, Date commentTime, String body) {
        this.postComment = postComment;
        this.commentByAccountId = commentByAccountId;
        this.commentTime = commentTime;
        this.body = body;
    }

    public PostComment(Long id, Post postComment, Account commentByAccountId, Date commentTime, String body) {
        this.id = id;
        this.postComment = postComment;
        this.commentByAccountId = commentByAccountId;
        this.commentTime = commentTime;
        this.body = body;
    }

    public PostComment() {
    }
}
