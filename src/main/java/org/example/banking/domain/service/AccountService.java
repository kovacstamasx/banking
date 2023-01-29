package org.example.banking.domain.service;

import org.example.banking.domain.model.account.Account;
import org.example.banking.domain.model.account.Account.AccountId;
import org.example.banking.domain.model.account.Client.ClientId;
import org.example.banking.domain.model.account.Currency;

import java.util.UUID;

import static java.math.BigDecimal.ZERO;

public class AccountService {

    public Account openAccount(ClientId actor, Currency currency) {
        return new Account(new AccountId(UUID.randomUUID()), ZERO, actor, currency);
    }
}
