package org.example.banking.infrastructure.configuration;

import org.example.banking.application.port.inbound.account.AccountFacade;
import org.example.banking.application.port.outbound.account.AccountRepository;
import org.example.banking.domain.service.AccountService;
import org.example.banking.infrastructure.adapter.outbound.account.AccountInMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public AccountFacade accountFacade(AccountService accountService, AccountRepository accountRepository) {
        return new AccountFacade(accountService, accountRepository);
    }

    @Bean
    public AccountService accountService() {
        return new AccountService();
    }

    @Bean
    public AccountRepository accountRepository() {
        return new AccountInMemoryRepository(new ConcurrentHashMap<>());
    }
}
