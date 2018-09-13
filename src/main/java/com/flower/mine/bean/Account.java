package com.flower.mine.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Account {

    @Id
    @Column(unique = true, length = 20)
    private String mobile;
    @Column(length = 64)
    @JsonIgnore
    private String password;
    @CreatedDate
    private Date createdTime;
    @LastModifiedDate
    private Date modifiedTime;
    @Column()
    private String invitedId;
    @Column(length = 32)
    private String salt;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getInvitedId() {
        return invitedId;
    }

    public void setInvitedId(String invitedId) {
        this.invitedId = invitedId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
