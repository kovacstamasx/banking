package org.example.banking.infrastructure.adapter.outbound.account;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

public class AccountAtomicIdSequence implements Supplier<Long> {

    private final AtomicLong atomicLong = new AtomicLong();

    @Override
    public Long get() {
        return atomicLong.get();
    }
}
