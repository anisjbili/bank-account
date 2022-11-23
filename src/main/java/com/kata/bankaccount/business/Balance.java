package com.kata.bankaccount.business;

import java.math.BigDecimal;

public final class Balance {

    private final BigDecimal value;

    public Balance(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

}
