package com.flower.mine.param;

import org.hibernate.validator.constraints.Length;

public class ResetPasswordParam {
    @Length(min = 8, max = 20)
    private String mobile;
    @Length(min = 6, max = 8)
    private String sms;
    @Length(min = 6)
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
