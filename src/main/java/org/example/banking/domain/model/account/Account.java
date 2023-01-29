package org.example.banking.domain.model.account;

import org.example.banking.domain.model.account.Client.ClientId;
import org.example.banking.domain.model.base.AggregateRootEntity;
import org.example.banking.domain.model.base.Id;

import java.math.BigDecimal;
import java.util.UUID;

import static java.math.BigDecimal.ZERO;

public class Account extends AggregateRootEntity<Account.AccountId> {

    private final AccountId id;
    private BigDecimal balance;
    private final ClientId owner;
    private final Currency currency;

    public Account(AccountId id, BigDecimal balance, ClientId owner, Currency currency) {
        this.id = id;
        this.balance = balance;
        this.owner = owner;
        this.currency = currency;
    }

    @Override
    public AccountId id() {
        return id;
    }

    public void transfer(ClientId actor, Account target, BigDecimal amount) {
        withdraw(actor, amount);
        target.deposit(amount);
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void withdraw(ClientId actor, BigDecimal amount) {
        if (!owner.equals(actor)) {
            throw new RuntimeException("Only owner can withdraw");
        }
        if (balance.subtract(amount).compareTo(ZERO) < 0) {
            throw new RuntimeException("Not enough balance");
        }

        balance = balance.subtract(amount);
    }

    public static class AccountId extends Id {
        public AccountId(UUID id) {
            super(id);
        }
    }
}
