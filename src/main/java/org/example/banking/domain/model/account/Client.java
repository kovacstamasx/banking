package org.example.banking.domain.model.account;

import org.example.banking.domain.model.base.Entity;
import org.example.banking.domain.model.base.Id;

import java.util.UUID;

public class Client extends Entity<Client.ClientId> {

    private final ClientId id;

    public Client(ClientId id) {
        this.id = id;
    }

    @Override
    public ClientId id() {
        return id;
    }

    public static class ClientId extends Id {
        public ClientId(UUID id) {
            super(id);
        }
    }
}
