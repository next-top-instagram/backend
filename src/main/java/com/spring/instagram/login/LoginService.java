package com.spring.instagram.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Random;

@RestController
@RequestMapping(path = "/api/login")
public class LoginService {

    @Resource
    private UserInfo userInfo;

    @PostMapping
    public String Login(@RequestBody LoginModel loginModel) {
        if (loginModel.getEmail().equals("hello@example.com") && loginModel.getPassword().equals("1234")) {
            userInfo.setUserNm(loginModel.getEmail());
            userInfo.setUserId(1L);
            return "OK";
        }
        return "Fail";
    }
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @GetMapping
    public String AmiLogin() {
        log.info("asdf: " + userInfo.getUserNm());
        if (userInfo.getUserNm() != null) {
            return userInfo.getUserNm();
        }
        return "Fail";
    }

    @DeleteMapping
    public String Logout(HttpSession session) {
        session.invalidate();
        return "Bye";
    }
}
