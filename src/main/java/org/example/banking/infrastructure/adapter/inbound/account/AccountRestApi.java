package org.example.banking.infrastructure.adapter.inbound.account;

import org.example.banking.application.port.inbound.account.AccountApi;
import org.example.banking.application.port.inbound.account.OpenAccountRequest;
import org.example.banking.application.port.inbound.account.OpenAccountResponse;
import org.example.banking.application.port.inbound.account.TransferRequest;
import org.example.banking.application.usecase.account.AccountUseCase;
import org.example.banking.domain.model.account.Account;
import org.example.banking.domain.model.account.Client;
import org.example.banking.domain.model.account.Currency;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/accounts")
public class AccountRestApi implements AccountApi {

    private final AccountUseCase accountUseCase;

    public AccountRestApi(AccountUseCase accountUseCase) {
        this.accountUseCase = accountUseCase;
    }

    @Override
    @PostMapping("/openAccount")
    public OpenAccountResponse openAccount(@RequestBody OpenAccountRequest request) {
        var account = accountUseCase.openAccount(new Client.ClientId(request.actor()),
                Currency.valueOf(request.currency()));

        return new OpenAccountResponse(account.id().getId());
    }

    @Override
    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferRequest request) {
        accountUseCase.transfer(new Client.ClientId(request.actor()),
                new Account.AccountId(request.source()),
                new Account.AccountId(request.target()),
                request.amount());
    }
}
