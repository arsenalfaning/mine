package com.flower.mine.param;

import org.hibernate.validator.constraints.Length;

public class LoginParam {
    @Length(min = 8, max = 20)
    private String mobile;
    @Length(min = 6)
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
