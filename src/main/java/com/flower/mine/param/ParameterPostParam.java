package com.flower.mine.param;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class ParameterPostParam {
    @Length(min = 1, max = 64)
    @NotBlank
    private String name;
    @Length(min = 1, max = 64)
    @NotBlank
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
