package com.flower.mine.bean;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Hashrate {

    public static final byte Electricity_Fee_Type_No_Fee = 0;
    public static final byte Electricity_Fee_Type_Has_Fee = 1;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer min; //最小交易量（单位：TH/s）

    @Column(precision = 12, scale = 10, nullable = false)
    private BigDecimal price; //每1TH/s的单价（单位：比特币/年）

    @Column(nullable = false)
    private Integer max; //最大交易量（单位：TH/s）

    @Column(nullable = false)
    private Integer total; //该产品总算力（单位：TH/s）
    @Column(nullable = false)
    private Integer balance; //剩余算力 （单位：TH/s）
    @Column(nullable = false)
    private Byte electricityFeeType; //电费类型
    @Column(precision = 12, scale = 10)
    private BigDecimal electricityFee; //电费 （单位：btc/TH/天）

    @Column(nullable = false)
    private Byte period; //产品年限

    private Date startTime; //开始时间

    private Date endTime; //结束时间

    @CreatedDate
    private Date createdTime;
    @LastModifiedDate
    private Date modifiedTime;
    @Column(nullable = false)
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Byte getElectricityFeeType() {
        return electricityFeeType;
    }

    public void setElectricityFeeType(Byte electricityFeeType) {
        this.electricityFeeType = electricityFeeType;
    }

    public BigDecimal getElectricityFee() {
        return electricityFee;
    }

    public void setElectricityFee(BigDecimal electricityFee) {
        this.electricityFee = electricityFee;
    }
}
