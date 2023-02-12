package org.example.banking.domain.model.account;

import org.example.banking.domain.model.account.Client.ClientId;
import org.example.banking.domain.model.base.AggregateRootEntity;
import org.example.banking.domain.model.base.Id;
import org.example.banking.domain.rule.Rule;
import org.example.banking.domain.rule.account.AccountWithdrawBalanceRule;
import org.example.banking.domain.rule.account.AccountWithdrawOwnerRule;

import java.math.BigDecimal;
import java.util.Set;

public class Account extends AggregateRootEntity<Account.AccountId> {

    private final AccountId id;
    private BigDecimal balance;
    private final ClientId owner;
    private final Currency currency;
    private final Set<Rule<WithdrawContext>> withdrawRules;

    public Account(AccountId id, BigDecimal balance, ClientId owner, Currency currency) {
        this.id = id;
        this.balance = balance;
        this.owner = owner;
        this.currency = currency;
        this.withdrawRules = Set.of(new AccountWithdrawOwnerRule(this), new AccountWithdrawBalanceRule(this));
    }

    @Override
    public AccountId id() {
        return id;
    }

    public void transfer(ClientId actor, Account target, BigDecimal amount) {
        withdraw(new WithdrawContext(actor, amount));
        target.deposit(amount);
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void withdraw(WithdrawContext context) {
        var ruleViolation = evaluateRules(withdrawRules, context);
        if (ruleViolation.isPresent()) {
            throw new RuntimeException(ruleViolation.get());
        }

        balance = balance.subtract(context.amount);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public ClientId getOwner() {
        return owner;
    }

    public static class AccountId extends Id {
        public AccountId(Long id) {
            super(id);
        }
    }

    public record WithdrawContext(ClientId actor, BigDecimal amount) {
    }
}
