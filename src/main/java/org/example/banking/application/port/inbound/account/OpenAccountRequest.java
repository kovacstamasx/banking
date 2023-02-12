package org.example.banking.application.port.inbound.account;

public record OpenAccountRequest(Long actor, String currency) {
}
