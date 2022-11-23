package com.kata.bankaccount.business;

import com.kata.bankaccount.infrastructure.StatementPrinter;

public class Account {

    private Balance balance;

    public Account(Balance balance) {
        this.balance = balance;
    }

    public Balance getBalance() {
        return balance;
    }

    public void deposit(Amount amount) {
        balance = balance.add(amount);
    }

    public void withdrawal(Amount amount) {
        balance = balance.subtract(amount);
    }

    public void print(StatementPrinter statementPrinter) {
        //TODO("Not implemented yet")
    }

}
