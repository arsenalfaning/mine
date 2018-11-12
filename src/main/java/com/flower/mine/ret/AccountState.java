package com.flower.mine.ret;

import com.flower.mine.bean.Account;

import java.util.List;

public class AccountState {
    private Account account; //账号
    private Integer hash; //算力
    private String address;
    private List<ChartVo> hashList; //算力表

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

    public List<ChartVo> getHashList() {
        return hashList;
    }

    public void setHashList(List<ChartVo> hashList) {
        this.hashList = hashList;
    }
}
