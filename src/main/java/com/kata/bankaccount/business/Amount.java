package com.kata.bankaccount.business;

import java.math.BigDecimal;
import java.util.Objects;

public final class Amount {

    private final BigDecimal value;

    public Amount(BigDecimal value) {
        Objects.requireNonNull(value);
        if(value.signum()==-1)
            throw new IllegalArgumentException("Amount must not be negative");
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

}
