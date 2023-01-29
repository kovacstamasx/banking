package org.example.banking.infrastructure.adapter.inbound.account;

import org.example.banking.application.port.inbound.account.AccountFacade;
import org.example.banking.application.port.inbound.account.OpenAccountRequest;
import org.example.banking.application.port.inbound.account.OpenAccountResponse;
import org.example.banking.application.port.inbound.account.TransferRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/accounts")
public class AccountController {

    private final AccountFacade accountFacade;

    public AccountController(AccountFacade accountFacade) {
        this.accountFacade = accountFacade;
    }

    @PostMapping("/openAccount")
    public OpenAccountResponse openAccount(@RequestBody OpenAccountRequest request) {
        return accountFacade.openAccount(request);
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferRequest request) {
        accountFacade.transfer(request);
    }
}
