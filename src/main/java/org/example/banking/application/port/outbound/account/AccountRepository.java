package org.example.banking.application.port.outbound.account;

import org.example.banking.domain.model.account.Account;
import org.example.banking.domain.model.account.Account.AccountId;

import java.util.Optional;

public interface AccountRepository {

    void addOne(Account account);

    Optional<Account> getOneById(AccountId id);

    void addAll(Account... accounts);
}
