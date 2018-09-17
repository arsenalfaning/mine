package com.flower.mine.ret;

import java.math.BigDecimal;

public class HashOrderResult {
    private String address; //收钱地址
    private BigDecimal cost; //订单总额，单位btc

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
