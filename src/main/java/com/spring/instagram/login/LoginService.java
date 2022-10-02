package com.spring.instagram.login;

import com.spring.instagram.models.BasicResponseModel;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Random;

@RestController
@RequestMapping(path = "/api/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginService {

    @Resource
    private UserInfo userInfo;

    @PostMapping
    public BasicResponseModel Login(@RequestBody LoginModel loginModel) {
        if (loginModel.getEmail().equals("hello@example.com") && loginModel.getPassword().equals("@Test1234")) {
            userInfo.setUserNm(loginModel.getEmail());
            userInfo.setUserId(1L);
            return new BasicResponseModel(true, "OK", null);
        }
        return new BasicResponseModel(false, "FAIL", null);
    }
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @GetMapping
    @SessionCheck
    @Parameter(name = "userName", hidden = true)
    public BasicResponseModel AmiLogin(String userName) {
        return new BasicResponseModel(true,  "Hi, " + userName, userName);
    }

    @DeleteMapping
    public BasicResponseModel Logout(HttpSession session) {
        session.invalidate();
        return new BasicResponseModel(true, "Bye", null);
    }
}
