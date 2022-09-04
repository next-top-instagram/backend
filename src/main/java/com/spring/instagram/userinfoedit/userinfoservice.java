package com.spring.instagram.userinfoedit;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
@RequestMapping(path = "/api/userinfoedit")
public class userinfoservice {
    @Resource
    private userinfo userinfo;

    public userinfoservice(@RequestBody userinfo userinfo) {
        this.userinfo = userinfo;
        
    }
}
