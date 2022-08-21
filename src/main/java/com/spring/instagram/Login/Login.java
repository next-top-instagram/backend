package com.spring.instagram.Login;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login {
    @Id
    private Long id;
    private String pwd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Login(Long id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

}
