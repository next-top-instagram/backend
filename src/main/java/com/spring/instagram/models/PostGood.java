package com.spring.instagram.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "post_good")
public class PostGood {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    @JsonIgnoreProperties(value = "postGoodList")
    private Post postGood;

    @OneToOne
    @JoinColumn(name = "vote_by_account_id", referencedColumnName = "account_id")
    private Account voteByAccountId;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date voteTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getVoteByAccountId() {
        return voteByAccountId;
    }

    public void setVoteByAccountId(Account voteByAccountId) {
        this.voteByAccountId = voteByAccountId;
    }

    public Date getVoteTime() {
        return voteTime;
    }

    public void setVoteTime(Date voteTime) {
        this.voteTime = voteTime;
    }

    public PostGood(Post postGood, Account voteByAccountId, Date voteTime) {
        this.postGood = postGood;
        this.voteByAccountId = voteByAccountId;
        this.voteTime = voteTime;
    }

    public PostGood(Long id, Post postGood, Account voteByAccountId, Date voteTime) {
        this.id = id;
        this.postGood = postGood;
        this.voteByAccountId = voteByAccountId;
        this.voteTime = voteTime;
    }

    @Override
    public String toString() {
        return "PostGood{" +
                "id=" + id +
                ", postGood=" + postGood +
                ", voteByAccountId=" + voteByAccountId +
                ", voteTime=" + voteTime +
                '}';
    }

    public Post getPostGood() {
        return postGood;
    }

    public void setPostGood(Post postGood) {
        this.postGood = postGood;
    }

    public PostGood() {
    }
}
