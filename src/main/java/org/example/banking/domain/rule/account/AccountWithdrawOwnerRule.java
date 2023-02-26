package org.example.banking.domain.rule.account;

import org.example.banking.domain.model.account.Account;
import org.example.banking.domain.model.account.Account.WithdrawContext;
import org.example.banking.domain.rule.Rule;

public class AccountWithdrawOwnerRule implements Rule<WithdrawContext> {

    private final Account account;

    public AccountWithdrawOwnerRule(Account account) {
        this.account = account;
    }

    @Override
    public boolean isViolatedBy(WithdrawContext context) {
        return !account.getOwner().equals(context.actor());
    }

    @Override
    public String getMessage() {
        return "Only owner can withdraw";
    }
}
