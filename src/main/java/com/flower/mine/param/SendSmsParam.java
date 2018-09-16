package com.flower.mine.param;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class SendSmsParam {
    @Length(min = 8, max = 20)
    @NotBlank
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
