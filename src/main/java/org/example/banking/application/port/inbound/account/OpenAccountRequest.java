package org.example.banking.application.port.inbound.account;

import java.util.UUID;

public record OpenAccountRequest(UUID actor, String currency) {
}
