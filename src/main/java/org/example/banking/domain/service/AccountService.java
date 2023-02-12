package org.example.banking.domain.service;

import org.example.banking.domain.model.account.Account;
import org.example.banking.domain.model.account.Account.AccountId;
import org.example.banking.domain.model.account.Client.ClientId;
import org.example.banking.domain.model.account.Currency;

import java.util.function.Supplier;

import static java.math.BigDecimal.ZERO;

public class AccountService {

    private final Supplier<Long> accountIdSequence;

    public AccountService(Supplier<Long> accountIdSequence) {
        this.accountIdSequence = accountIdSequence;
    }

    public Account openAccount(ClientId owner, Currency currency) {
        return new Account(new AccountId(accountIdSequence.get()), ZERO, owner, currency);
    }
}
