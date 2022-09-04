package com.spring.instagram.Signup;

public class Signup {
    private Integer id;
    private String Email;
    private String Password1;
    private  String Password2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword1() {
        return Password1;
    }

    public void setPassword1(String password1) {
        Password1 = password1;
    }

    public String getPassword2() {
        return Password2;
    }

    public void setPassword2(String password2) {
        Password2 = password2;
    }

    @Override
    public String toString() {
        return "Signup{" +
                "id=" + id +
                ", Email='" + Email + '\'' +
                ", Password1='" + Password1 + '\'' +
                ", Password2='" + Password2 + '\'' +
                '}';
    }

    public Signup(Integer id, String email, String password1, String password2) {
        this.id = id;
        Email = email;
        Password1 = password1;
        Password2 = password2;
    }

    public Signup(String email, String password1, String password2) {
        Email = email;
        Password1 = password1;
        Password2 = password2;
    }
}
