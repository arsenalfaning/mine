package com.flower.mine.param;

import javax.validation.constraints.NotNull;

public class HashratePutParam extends HashratePostParam {
    @NotNull
    private Long id;
    @NotNull
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
