package com.flower.mine.param;

import org.hibernate.validator.constraints.Length;

public class SendSmsParam {
    @Length(min = 8, max = 20)
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
