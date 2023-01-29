package org.example.banking.application.port.inbound.account;

import org.example.banking.application.port.outbound.account.AccountRepository;
import org.example.banking.domain.model.account.Account.AccountId;
import org.example.banking.domain.model.account.Client.ClientId;
import org.example.banking.domain.model.account.Currency;
import org.example.banking.domain.service.AccountService;

public class AccountFacade {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    public AccountFacade(AccountService accountService, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    public OpenAccountResponse openAccount(OpenAccountRequest request) {
        var account = accountService.openAccount(new ClientId(request.actor()), Currency.valueOf(request.currency()));

        accountRepository.addOne(account);

        return new OpenAccountResponse(account.id().getId());
    }

    public void transfer(TransferRequest request) {
        var sourceAccount = accountRepository.getOneById(new AccountId(request.source()))
                .orElseThrow(() -> new RuntimeException("Source account not found by id: " + request.source()));
        var targetAccount = accountRepository.getOneById(new AccountId(request.target()))
                .orElseThrow(() -> new RuntimeException("Target account not found by id: " + request.target()));

        sourceAccount.transfer(new ClientId(request.actor()), targetAccount, request.amount());

        accountRepository.addAll(sourceAccount, targetAccount);
    }
}
