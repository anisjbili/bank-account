package com.kata.bankaccount.business;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Operation {

    private final OperationType type;
    private final Amount amount;
    private final LocalDateTime date;

    public Operation(OperationType type, Amount amount, LocalDateTime date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public OperationType getType() {
        return type;
    }
    public Amount getAmount() {
        return amount;
    }
    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return getType() == operation.getType() && Objects.equals(getAmount(), operation.getAmount()) && Objects.equals(getDate(), operation.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getAmount(), getDate());
    }
}
