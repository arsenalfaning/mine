package com.flower.mine.ret;

import com.flower.mine.bean.Account;

public class AccountState {
    private Account account; //账号
    private Integer hash; //算力

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }
}
