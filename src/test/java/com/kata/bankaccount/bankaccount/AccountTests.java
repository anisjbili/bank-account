package com.kata.bankaccount.bankaccount;

import com.kata.bankaccount.business.Account;
import com.kata.bankaccount.business.Amount;
import com.kata.bankaccount.business.Balance;
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
}
