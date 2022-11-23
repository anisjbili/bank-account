package com.kata.bankaccount.business;

import com.kata.bankaccount.exceptions.InsufficientFundsException;

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

    public Balance subtract(Amount amount) {
        if (value.compareTo(amount.getValue()) < 0) {
            throw new InsufficientFundsException("Balance is insufficient to make this debit");
        }
        return new Balance(value.subtract(amount.getValue()));
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
