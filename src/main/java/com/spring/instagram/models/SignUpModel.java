package com.spring.instagram.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpModel {
    private String email;
    private String password;

    public SignUpModel(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
