package com.spring.instagram.userinfoedit;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
public class userinfo {
    private String userNm;
    private Long userId;
    private String userInfo;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date userBirthTime;
    //이름, 아이디, 정보, 생년월일//


    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public Date getUserBirthTime() {
        return userBirthTime;
    }

    public void setUserBirthTime(Date userBirthTime) {
        this.userBirthTime = userBirthTime;
    }


    @Override
    public String toString() {
        return "userinfo{" +
                "userNm='" + userNm + '\'' +
                ", userId=" + userId +
                ", userInfo='" + userInfo + '\'' +
                ", userBirthTime=" + userBirthTime +
                '}';
    }
}
