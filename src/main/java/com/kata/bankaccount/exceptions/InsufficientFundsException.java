package com.kata.bankaccount.exceptions;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message)
    {
        super(message);
    }
}

