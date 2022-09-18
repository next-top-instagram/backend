package com.spring.instagram.Signup;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/signup")
public class SignupService {
    private SignupRepository signupRepository;

    @Autowired
    public SignupService (SignupRepository signupRepository) {
        this.signupRepository = signupRepository;
    }

    @PostMapping
    public String FindEmail (@RequestBody Signup signup) {
        if (signup.getEmail().equals("example@gmail.com")) {
            return "중복된 이메일입니다.";
        } else {

        }

    }
}
