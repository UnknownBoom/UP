package com.JSKP.KP.model;

import java.math.BigDecimal;

public class MyBigDecimal {
    private BigDecimal decimal;


    public BigDecimal getDecimal() {
        return decimal;
    }

    public MyBigDecimal() {
    }

    public void setDecimal(BigDecimal decimal) {
        this.decimal = decimal;
    }

    public MyBigDecimal(BigDecimal decimal) {
        this.decimal = decimal;
    }
}
