package org.example.banking.domain.model.base;

import java.util.Objects;

public abstract class Entity<T> {

    public abstract T id();

    @Override
    public int hashCode() {
        return Objects.hash(id());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity<?> other = (Entity<?>) o;
        return id().equals(other.id());
    }
}
