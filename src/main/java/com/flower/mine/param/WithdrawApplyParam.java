package com.flower.mine.param;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class WithdrawApplyParam {

    @NotNull
    private BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
