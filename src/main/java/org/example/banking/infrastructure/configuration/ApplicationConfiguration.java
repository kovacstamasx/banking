package org.example.banking.infrastructure.configuration;

import org.example.banking.application.port.outbound.account.AccountRepository;
import org.example.banking.application.usecase.account.AccountUseCase;
import org.example.banking.domain.service.AccountService;
import org.example.banking.infrastructure.adapter.outbound.account.AccountAtomicIdSequence;
import org.example.banking.infrastructure.adapter.outbound.account.AccountInMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public AccountUseCase accountUseCase(AccountService accountService, AccountRepository accountRepository) {
        return new AccountUseCase(accountService, accountRepository);
    }

    @Bean
    public AccountService accountService(Supplier<Long> accountIdSequence) {
        return new AccountService(accountIdSequence);
    }

    @Bean
    public AccountRepository accountRepository() {
        return new AccountInMemoryRepository(new ConcurrentHashMap<>());
    }

    @Bean
    public Supplier<Long> accountIdSequence() {
        return new AccountAtomicIdSequence();
    }
}
