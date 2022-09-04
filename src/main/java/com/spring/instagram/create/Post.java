package com.spring.instagram.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.instagram.library.Book;
import com.spring.instagram.login.UserInfo;
import org.apache.catalina.User;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", write_by=" + userInfo +
                ", image_id=" + image_id +
                ", good_cnt=" + good_cnt +
                ", create_time=" + create_time +
                '}';
    }

    public Post(Long id, String body, UserInfo userInfo, Long image_id, Long good_cnt, Date create_time) {
        this.id = id;
        this.body = body;
        this.userInfo = userInfo;
        this.image_id = image_id;
        this.good_cnt = good_cnt;
        this.create_time = create_time;
    }

    public Post(String body, UserInfo userInfo, Long image_id, Long good_cnt, Date create_time) {
        this.body = body;
        this.userInfo = userInfo;
        this.image_id = image_id;
        this.good_cnt = good_cnt;
        this.create_time = create_time;
    }

    public Post() {
    }
    @NotNull 
    @ManyToOne
    @JoinColumn(name = "userInfo_id")
    private UserInfo userInfo;
    @NotNull
    private Long image_id;
    @ColumnDefault("0")
    private Long good_cnt;

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

    public UserInfo getWrite_by() {
        return userInfo;
    }

    public void setWrite_by(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Long getImage_id() {
        return image_id;
    }

    public void setImage_id(Long image_id) {
        this.image_id = image_id;
    }

    public Long getGood_cnt() {
        return good_cnt;
    }

    public void setGood_cnt(Long good_cnt) {
        this.good_cnt = good_cnt;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date create_time;
}
