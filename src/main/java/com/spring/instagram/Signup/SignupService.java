package com.spring.instagram.Signup;


import org.springframework.beans.factory.annotation.Autowired;
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

    public
}
