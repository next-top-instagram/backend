/*package com.spring.instagram.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.instagram.create.Post;
import com.spring.instagram.login.UserInfo;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;
    @ManyToOne
    @JoinColumn(name="comment_by")
    private UserInfo userInfo;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date comment_time;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", post=" + post +
                ", userInfo=" + userInfo +
                ", comment_time=" + comment_time +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Date getComment_time() {
        return comment_time;
    }

    public void setComment_time(Date comment_time) {
        this.comment_time = comment_time;
    }

    public Comment(Long id, Post post, UserInfo userInfo, Date comment_time) {
        this.id = id;
        this.post = post;
        this.userInfo = userInfo;
        this.comment_time = comment_time;
    }

    public Comment(Post post, UserInfo userInfo, Date comment_time) {
        this.post = post;
        this.userInfo = userInfo;
        this.comment_time = comment_time;
    }

    public Comment() {
    }
}
*/