package com.kata.bankaccount.business;

import com.kata.bankaccount.infrastructure.StatementPrinter;

import java.time.Clock;
import java.time.LocalDateTime;

public class Account {

    private Balance balance;
    private Statement statement = new Statement();
    private Clock clock;

    public Account(Balance balance, Clock clock) {
        this.balance = balance;
        this.clock = clock;
    }

    public Account(Balance balance) {
        this.balance = balance;
        this.clock = Clock.systemDefaultZone();
    }

    public Balance getBalance() {
        return balance;
    }

    public void deposit(Amount amount) {
        balance = balance.add(amount);
        statement.add(new Operation(OperationType.DEPOSIT, amount, LocalDateTime.now(clock)), balance);
    }

    public void withdrawal(Amount amount) {
        balance = balance.subtract(amount);
        statement.add(new Operation(OperationType.WITHDRAWAL, amount, LocalDateTime.now(clock)), balance);
    }

    public void print(StatementPrinter statementPrinter) {
        statementPrinter.print(statement);
    }
}
