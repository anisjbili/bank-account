package com.kata.bankaccount.business;

import java.math.BigDecimal;
import java.util.Objects;

public final class Balance {

    private final BigDecimal value;

    public Balance(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Balance add(Amount amount) {
        return new Balance(value.add(amount.getValue()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance balance = (Balance) o;
        return Objects.equals(value, balance.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
