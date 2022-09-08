package com.spring.instagram.login;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Component
@Entity
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userNm;

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userNm='" + userNm + '\'' +
                '}';
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserNm() {
        return userNm;
    }
}
