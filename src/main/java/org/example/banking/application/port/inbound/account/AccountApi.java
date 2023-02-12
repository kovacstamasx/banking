package org.example.banking.application.port.inbound.account;

public interface AccountApi {

    OpenAccountResponse openAccount(OpenAccountRequest request);

    void transfer(TransferRequest request);

}
