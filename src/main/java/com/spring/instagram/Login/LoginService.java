package com.spring.instagram.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/Login")
public class LoginService {
    private LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @PostMapping
    public String Login(@RequestBody Login login) {
        try {
            this.loginRepository.save(login); //insert
            return "OK";
        } catch (Exception err) {
            return "Fail";
        }
    }

    @GetMapping
    public String GetId(@RequestBody Login login) {
        try {
            Optional<Login> loginOptional = this.loginRepository.findById(login.getId());//id가져오기
            if (loginOptional.isPresent()) {
                Login updateLogin = loginOptional.get();//set으로 body에 있던거 가져오고 save
                updateLogin.setId(login.getId()); //id
                updateLogin.setPwd(login.getPwd()); //비번
                this.loginRepository.save(updateLogin);
            } else {
                return "Fail";
            }
        } catch (Exception err) {
            return "Fail";
        }
        return "ok";
    }

    @DeleteMapping
    public String LogOut(@PathVariable Long id) {
        try {
            Optional<Login> loginOptional = this.loginRepository.findById(id); //id로 찾기
            if (loginOptional.isPresent()) { //있으면
                this.loginRepository.delete(loginOptional.get()); //지워주기
            } else {
                return "Fail";
            }
        } catch (Exception err) {
            return "Fail";
        }
        return "OK";
    }
}
