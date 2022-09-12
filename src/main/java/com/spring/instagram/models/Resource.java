package com.spring.instagram.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "resource")
public class Resource {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "resource_id")
    private Long id;
    @Column(nullable = false)
    private String contentType;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;
    private String url;

    @Override
    public String toString() {
        return "resource{" +
                "id=" + id +
                ", contentType='" + contentType + '\'' +
                ", publishTime=" + uploadTime +
                ", url='" + url + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Resource(Long id, String contentType, Date uploadTime, String url) {
        this.id = id;
        this.contentType = contentType;
        this.uploadTime = uploadTime;
        this.url = url;
    }

    public Resource(String contentType, Date uploadTime, String url) {
        this.contentType = contentType;
        this.uploadTime = uploadTime;
        this.url = url;
    }

    public Resource() {
    }
}
