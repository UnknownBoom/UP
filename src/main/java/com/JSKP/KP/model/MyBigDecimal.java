package com.JSKP.KP.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@NoArgsConstructor
public class MyBigDecimal {
    private BigDecimal decimal;

    public BigDecimal getDecimal() {
        return decimal;
    }

    public void setDecimal(BigDecimal decimal) {
        this.decimal = decimal;
    }

    public MyBigDecimal(BigDecimal decimal) {
        this.decimal = decimal;
    }
}
