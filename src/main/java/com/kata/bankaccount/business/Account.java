package com.kata.bankaccount.business;

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
}
