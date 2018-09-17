package com.flower.mine.bean;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 算力订单表
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
public class HashOrder {

    public static final int Status_Unpaid = 0; //未付款
    public static final int  Status_Paid = 1; //已付款

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 20)
    private String username; //订单所属用户

    @Column(nullable = false)
    private Integer hash; //算力，单位TH/s

    @Column(nullable = false)
    private Byte period; //时间，单位年

    @Column(nullable = false, precision = 20, scale = 10)
    private BigDecimal cost; //初期成本，单位btc

    @Column(nullable = false, precision = 20, scale = 10)
    private BigDecimal fee; //日常费用，单位btc

    @Column(nullable = false)
    private Integer state; //订单状态

    private Date startTime; //生效日期

    @CreatedDate
    private Date createdTime;
    @LastModifiedDate
    private Date modifiedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }

    public Byte getPeriod() {
        return period;
    }

    public void setPeriod(Byte period) {
        this.period = period;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

}
