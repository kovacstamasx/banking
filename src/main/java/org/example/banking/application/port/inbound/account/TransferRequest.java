package org.example.banking.application.port.inbound.account;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferRequest(UUID source, UUID actor, UUID target, BigDecimal amount) {
}
