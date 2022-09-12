package com.spring.instagram.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    @Column(nullable = false)
    private String body;
    @ManyToOne
    @JoinColumn(name = "write_by", referencedColumnName = "account_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Account writeBy;
    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "resource_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Resource imageId;

    @OneToMany(mappedBy = "postComment")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PostComment> postCommentList = new ArrayList<>();

    @OneToMany(mappedBy = "postGood")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PostGood> postGoodList = new ArrayList<>();


    @Column(name = "good_cnt")
    @ColumnDefault("0")
    private int goodCnt;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Account getWriteBy() {
        return writeBy;
    }

    public void setWriteBy(Account writeBy) {
        this.writeBy = writeBy;
    }

    public Resource getImageId() {
        return imageId;
    }

    public void setImageId(Resource imageId) {
        this.imageId = imageId;
    }

    public List<PostComment> getPostCommentList() {
        return postCommentList;
    }

    public void setPostCommentList(List<PostComment> postCommentList) {
        this.postCommentList = postCommentList;
    }

    public List<PostGood> getPostGoodList() {
        return postGoodList;
    }

    public void setPostGoodList(List<PostGood> postGoodList) {
        this.postGoodList = postGoodList;
    }

    public int getGoodCnt() {
        return goodCnt;
    }

    public void setGoodCnt(int goodCnt) {
        this.goodCnt = goodCnt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Post(String body, Account writeBy, Resource imageId, List<PostComment> postCommentList, List<PostGood> postGoodList, int goodCnt, Date createTime) {
        this.body = body;
        this.writeBy = writeBy;
        this.imageId = imageId;
        this.postCommentList = postCommentList;
        this.postGoodList = postGoodList;
        this.goodCnt = goodCnt;
        this.createTime = createTime;
    }

    public Post(Long id, String body, Account writeBy, Resource imageId, List<PostComment> postCommentList, List<PostGood> postGoodList, int goodCnt, Date createTime) {
        this.id = id;
        this.body = body;
        this.writeBy = writeBy;
        this.imageId = imageId;
        this.postCommentList = postCommentList;
        this.postGoodList = postGoodList;
        this.goodCnt = goodCnt;
        this.createTime = createTime;
    }

    public Post() {
    }
}
