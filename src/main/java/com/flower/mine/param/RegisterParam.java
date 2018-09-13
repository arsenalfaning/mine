package com.flower.mine.param;

import org.hibernate.validator.constraints.Length;

public class RegisterParam {
    @Length(min = 8, max = 20)
    private String mobile;
    @Length(min = 6)
    private String password;
    @Length(min = 6, max = 8)
    private String sms;
    @Length(min = 8, max = 20)
    private String invitedId;

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

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getInvitedId() {
        return invitedId;
    }

    public void setInvitedId(String invitedId) {
        this.invitedId = invitedId;
    }
}
