package com.kata.bankaccount.business;

import java.math.BigDecimal;

public final class Amount {

    private final BigDecimal value;

    public Amount(BigDecimal value) {
       this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

}
