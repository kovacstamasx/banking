package org.example.banking.domain.model.base;

import org.example.banking.domain.rule.Rule;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public abstract class Entity<T> {

    public abstract T id();

    protected <C> Optional<String> evaluateRules(Set<Rule<C>> rules, C context) {
        return rules.stream()
                .filter(rule -> rule.isViolatedBy(context))
                .map(Rule::getMessage)
                .reduce((a, b) -> a + ", " + b);
    }

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
