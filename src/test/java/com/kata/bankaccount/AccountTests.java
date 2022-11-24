package com.kata.bankaccount;

import com.kata.bankaccount.business.*;
import com.kata.bankaccount.exceptions.InsufficientFundsException;
import com.kata.bankaccount.infrastructure.StatementPrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class AccountTests {

    private final static Clock fixedClock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

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

    @Test
    void should_an_account_with_balance_0_have_balance_of_10_when_deposit_amount_of_20_and_withdrawal_an_amount_of_10() {
        Balance balance = new Balance(BigDecimal.TEN);
        Account account = new Account(balance);
        Amount amount = new Amount(BigDecimal.TEN);
        account.withdrawal(amount);
        Balance expectedBalance = new Balance(BigDecimal.ZERO);
        Assertions.assertEquals(expectedBalance, account.getBalance());
    }

    @Test
    void making_a_deposit_and_withdrawal_and_printing_statement() {
        Balance balance = new Balance(BigDecimal.ZERO);
        Account account = new Account(balance, fixedClock);
        // Make a deposit
        Amount amountDeposit = new Amount(BigDecimal.valueOf(20));
        account.deposit(amountDeposit);

        // Make a withdrawal
        Amount amountWithdrawal = new Amount(BigDecimal.valueOf(10));
        account.withdrawal(amountWithdrawal);

        List<StatementLine> statementLineList = new ArrayList<>();
        StatementLine statementLine = new StatementLine(new Operation(OperationType.DEPOSIT, amountDeposit, LocalDateTime.now(fixedClock)), new Balance(BigDecimal.valueOf(20)));
        statementLineList.add(statementLine);
        statementLine = new StatementLine(new Operation(OperationType.WITHDRAWAL, amountWithdrawal, LocalDateTime.now(fixedClock)), new Balance(BigDecimal.TEN));
        statementLineList.add(statementLine);

        FakeStatementPrinter statementPrinter=new FakeStatementPrinter();
        account.print(statementPrinter);
        Assertions.assertTrue(statementPrinter.lines.equals(statementLineList));
    }

    private static class FakeStatementPrinter implements StatementPrinter {

        private final List<StatementLine> lines = new ArrayList<>();

        @Override
        public void print(Statement statement) {
            lines.addAll(statement.statementLines());
        }
    }
}
