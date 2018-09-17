package com.flower.mine.param;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class AddressParam {
    @NotNull
    @Length(min = 20, max = 64)
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
