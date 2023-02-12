package org.example.banking.domain.rule.account;

import org.example.banking.domain.model.account.Account;
import org.example.banking.domain.model.account.Account.WithdrawContext;
import org.example.banking.domain.rule.Rule;

import static java.math.BigDecimal.ZERO;

public class AccountWithdrawBalanceRule implements Rule<WithdrawContext> {

    private final Account account;

    public AccountWithdrawBalanceRule(Account account) {
        this.account = account;
    }

    @Override
    public boolean isViolatedBy(WithdrawContext context) {
        return account.getBalance().subtract(context.amount()).compareTo(ZERO) < 0;
    }

    @Override
    public String getMessage() {
        return "Not enough balance";
    }
}
