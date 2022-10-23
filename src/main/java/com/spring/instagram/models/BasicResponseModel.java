package com.spring.instagram.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicResponseModel {
    private boolean status;
    private String message;

    public BasicResponseModel(boolean status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    private Object data;
}
