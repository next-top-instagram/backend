package com.spring.instagram.Account;

import com.spring.instagram.login.SessionCheck;
import com.spring.instagram.models.Account;
import com.spring.instagram.models.AccountRepository;
import com.spring.instagram.models.BasicResponseModel;
import com.spring.instagram.models.SignUpModel;
import io.swagger.v3.oas.annotations.Parameter;
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

        Long cnt = accountRepository.isAccountAlreadyExist(data.getEmail());
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
    @PutMapping
    @SessionCheck
    @Parameter(name = "userName", hidden = true)
    public BasicResponseModel updatePassword(String userName, @RequestBody SignUpModel data) {
        try {
//            Optional<Account> account = accountRepository.fin
            System.out.println("test email: " + userName);
            accountRepository.updateAccountPassword(userName, data.getPassword());
            return new BasicResponseModel(true, "OK", null);
        } catch (Exception e) {
            return new BasicResponseModel(false, e.getMessage(), null);
        }
    }
}
