package com.spring.instagram.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class PostItemModel {
    @Id
    private Long id;
    private String body;
    private Date createTime;
    private int goodCnt;
    private String email;
    private String url;
//    private String user;

    public PostItemModel(Long id, String body, Date createTime, int goodCnt, String email, String url) {
        this.id = id;
        this.body = body;
        this.createTime = createTime;
        this.goodCnt = goodCnt;
        this.email = email;
        this.url = url;
    }

    public PostItemModel() {
    }
}
