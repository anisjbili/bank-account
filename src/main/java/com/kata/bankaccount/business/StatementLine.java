package com.kata.bankaccount.business;

import java.util.Objects;

public final class StatementLine {
    private final Operation operation;
    private final Balance balance;

    public StatementLine(Operation operation, Balance balance) {
        this.operation = operation;
        this.balance = balance;
    }

    public Operation getOperation() {
        return operation;
    }

    public Balance getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatementLine that = (StatementLine) o;
        return Objects.equals(getOperation(), that.getOperation()) && Objects.equals(getBalance(), that.getBalance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOperation(), getBalance());
    }
}
