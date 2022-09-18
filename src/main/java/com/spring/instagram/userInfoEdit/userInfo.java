package com.spring.instagram.userInfoEdit;

public class userInfo {
    private String password;
    private String newPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "userInfo{" +
                "password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
