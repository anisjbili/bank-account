package com.kata.bankaccount.bankaccount;

import com.kata.bankaccount.business.Account;
import com.kata.bankaccount.business.Amount;
import com.kata.bankaccount.business.Balance;
import com.kata.bankaccount.exceptions.InsufficientFundsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class AccountTests {

    @Test
    void should_new_account_have_balance_of_10_when_deposit_an_amount_of_10() {
        Balance balance = new Balance(BigDecimal.ZERO);
        Account account = new Account(balance);
        Amount amount = new Amount(BigDecimal.TEN);
        account.deposit(amount);
        Balance expectedBalance = new Balance(BigDecimal.TEN);
        Assertions.assertEquals(expectedBalance, account.getBalance());
    }

    @Test
    void should_an_account_with_balance_10_have_balance_of_30_when_deposit_an_amount_of_20() {
        Balance balance = new Balance(BigDecimal.TEN);
        Account account = new Account(balance);
        Amount amount = new Amount(BigDecimal.valueOf(20));
        account.deposit(amount);
        Balance expectedBalance = new Balance(BigDecimal.valueOf(30));
        Assertions.assertEquals(expectedBalance, account.getBalance());
    }

    @Test
    void making_a_deposit_of_negative_amount_should_be_invalid() {
        Balance balance = new Balance(BigDecimal.ZERO);
        Account account = new Account(balance);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Amount amount = new Amount(BigDecimal.valueOf(-10));
            account.deposit(amount);
        });
    }

    @Test
    void should_an_account_with_balance_10_have_balance_of_0_when_withdrawal_an_amount_of_10() {
        Balance balance = new Balance(BigDecimal.TEN);
        Account account = new Account(balance);
        Amount amount = new Amount(BigDecimal.TEN);
        account.withdrawal(amount);
        Balance expectedBalance = new Balance(BigDecimal.ZERO);
        Assertions.assertEquals(expectedBalance, account.getBalance());
    }

    @Test
    void making_a_withdrawal_of_an_amount_bigger_than_balance_should_be_invalid() {
        Balance balance = new Balance(BigDecimal.TEN);
        Account account = new Account(balance);
        Amount amount = new Amount(BigDecimal.valueOf(20));
        Assertions.assertThrows(InsufficientFundsException.class, () -> {
            account.withdrawal(amount);
        });
    }
}
