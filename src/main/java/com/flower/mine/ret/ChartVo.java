package com.flower.mine.ret;

import java.math.BigDecimal;

public class ChartVo {

    private String day;
    private BigDecimal value;

    public ChartVo() {
    }

    public ChartVo(String day, BigDecimal value) {
        this.day = day;
        this.value = value;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
