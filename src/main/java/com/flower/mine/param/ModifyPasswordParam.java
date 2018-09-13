package com.flower.mine.param;

import org.hibernate.validator.constraints.Length;

public class ModifyPasswordParam {
    @Length(min = 6)
    private String password;
    @Length(min = 6)
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
}
