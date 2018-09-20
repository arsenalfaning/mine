package com.flower.mine.ret;

import com.flower.mine.bean.Account;

public class AccountState {
    private Account account; //账号
    private Integer hash; //算力
    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
