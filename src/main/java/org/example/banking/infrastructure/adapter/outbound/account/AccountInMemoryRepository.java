package org.example.banking.infrastructure.adapter.outbound.account;

import org.example.banking.application.port.outbound.account.AccountRepository;
import org.example.banking.domain.model.account.Account;
import org.example.banking.domain.model.account.Account.AccountId;

import java.util.Map;
import java.util.Optional;

import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;

public class AccountInMemoryRepository implements AccountRepository {

    private final Map<AccountId, Account> store;

    public AccountInMemoryRepository(Map<AccountId, Account> store) {
        this.store = store;
    }

    @Override
    public void addOne(Account account) {
        store.put(account.id(), account);
    }

    @Override
    public Optional<Account> getOneById(AccountId id) {
        return ofNullable(store.get(id));
    }

    @Override
    public void addAll(Account... accounts) {
        stream(accounts).forEach(this::addOne);
    }
}
