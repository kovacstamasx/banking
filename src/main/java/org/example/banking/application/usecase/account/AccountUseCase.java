package org.example.banking.application.usecase.account;

import org.example.banking.application.port.outbound.account.AccountRepository;
import org.example.banking.domain.model.account.Account;
import org.example.banking.domain.model.account.Account.AccountId;
import org.example.banking.domain.model.account.Client.ClientId;
import org.example.banking.domain.model.account.Currency;
import org.example.banking.domain.service.AccountService;

import java.math.BigDecimal;

public class AccountUseCase {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    public AccountUseCase(AccountService accountService, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    public Account openAccount(ClientId owner, Currency currency) {
        var account = accountService.openAccount(owner, currency);

        accountRepository.addOne(account);

        return account;
    }

    public void transfer(ClientId actor, AccountId source, AccountId target, BigDecimal amount) {
        var sourceAccount = accountRepository.getOneById(source)
                .orElseThrow(() -> new RuntimeException("Source account not found by id: " + source));
        var targetAccount = accountRepository.getOneById(target)
                .orElseThrow(() -> new RuntimeException("Target account not found by id: " + target));

        sourceAccount.transfer(actor, targetAccount, amount);

        accountRepository.addAll(sourceAccount, targetAccount);
    }
}
