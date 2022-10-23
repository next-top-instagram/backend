package com.spring.instagram.login;

import com.spring.instagram.models.AccountRepository;
import com.spring.instagram.models.BasicResponseModel;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;

@RestController
@RequestMapping(path = "/api/login")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginService {

    @Autowired
    private final AccountRepository accountRepository;

    public LoginService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Resource
    private UserInfo userInfo;

    @PostMapping
    public ResponseEntity<BasicResponseModel> Login(@RequestBody LoginModel loginModel) {
        Long cnt = accountRepository.authenticationViaEmailAndPassword(loginModel.getEmail(), loginModel.getPassword());
        if (cnt <= 0) {
            return new ResponseEntity<>(new BasicResponseModel(false, "Email and password mismatch or not registered", null), HttpStatus.UNAUTHORIZED);
        }
        userInfo.setUserNm(loginModel.getEmail());
        userInfo.setUserId(1L);
        return new ResponseEntity<>(new BasicResponseModel(true, "OK", null), HttpStatus.OK);
    }
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @GetMapping
    @SessionCheck
    @Parameter(name = "userName", hidden = true)
    public BasicResponseModel AmiLogin(String userName) {
        System.out.println("Can u see me?");
        return new BasicResponseModel(true,  "Hi, " + userName, userName);
    }

    @DeleteMapping
    public BasicResponseModel Logout(HttpSession session) {
        session.invalidate();
        return new BasicResponseModel(true, "Bye", null);
    }
}
