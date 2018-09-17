package com.flower.mine.param;

import javax.validation.constraints.NotNull;

public class HashOrderParam {
    @NotNull
    private Long hashRateId; //算力产品id
    @NotNull
    private Integer hash; //购买的算力量

    public Long getHashRateId() {
        return hashRateId;
    }

    public void setHashRateId(Long hashRateId) {
        this.hashRateId = hashRateId;
    }

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }
}
