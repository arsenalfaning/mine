package com.flower.mine.ret;

import java.math.BigDecimal;

public class StatVo {
    private String date;
    private BigDecimal value;

    public StatVo() {
    }

    public StatVo(String date, BigDecimal value) {
        this.date = date;
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
