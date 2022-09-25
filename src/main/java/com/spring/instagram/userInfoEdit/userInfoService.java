package com.spring.instagram.userInfoEdit;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
@RequestMapping(path = "/api/userinfoedit")
public class userinfoservice {
    @Resource
    private userInfo userinfo;

    public userinfoservice(@RequestBody userInfo userinfo) {
        this.userinfo = userinfo;
        
    }
}
