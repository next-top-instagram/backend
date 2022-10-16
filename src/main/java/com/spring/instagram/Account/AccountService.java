package com.spring.instagram.Account;

import com.spring.instagram.models.Account;
import com.spring.instagram.models.AccountRepository;
import com.spring.instagram.models.BasicResponseModel;
import com.spring.instagram.models.SignUpModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/account")
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostMapping
    public BasicResponseModel signUp(@RequestBody SignUpModel data) {
//        data.getEmail();
//        data.getPassword();

        Long cnt = accountRepository.findAccountByEmail(data.getEmail());
        if (cnt > 0) {
            return new BasicResponseModel(false, "Already exist email", null);
        }
        try {
            accountRepository.save(new Account(data.getEmail(), data.getPassword()));
            return new BasicResponseModel(true, "Welcome!", null);
        } catch (Exception e) {
            return new BasicResponseModel(false, e.getMessage(), null);
        }
    }
}
