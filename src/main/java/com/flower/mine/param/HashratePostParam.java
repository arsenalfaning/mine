package com.flower.mine.param;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

public class HashratePostParam {
    @Min(1)
    @Max(1000)
    @NotNull
    private Integer min; //最小交易量（单位：TH/s）
    @DecimalMin(value = "0.0000000001")
    @DecimalMax(value = "99")
    @NotNull
    private BigDecimal price; //每1TH/s的单价（单位：比特币）
    @Min(1)
    @Max(Integer.MAX_VALUE)
//    @NotNull
    private Integer max; //最大交易量（单位：TH/s）
    @Min(1)
    @Max(Integer.MAX_VALUE)
    @NotNull
    private Integer total; //该产品总算力（单位：TH/s）
    @Min(value = 1)
    @Max(value = 10)
    @NotNull
    private Byte period;//产品年限
//    @NotNull
    private Date startTime;//开始时间

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Byte getPeriod() {
        return period;
    }

    public void setPeriod(Byte period) {
        this.period = period;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
