package com.spring.instagram.Account;

import com.spring.instagram.models.BasicResponseModel;
import com.spring.instagram.models.SignUpModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/account")
public class AccountService {

    @PostMapping
    public BasicResponseModel signUp(@RequestBody SignUpModel data) {
//        data.getEmail();
//        data.getPassword();
        return new BasicResponseModel(false, "Asdf", null);
    }
}
