package org.example.banking.application.port.inbound.account;

import java.math.BigDecimal;

public record TransferRequest(Long source, Long actor, Long target, BigDecimal amount) {
}
