package org.example.banking.domain.rule;

import java.util.Set;

public class RuleViolation {

    private final String message;

    private RuleViolation() {
        this(null);
    }

    private RuleViolation(String message) {
        this.message = message;
    }

    public static <C> RuleViolation evaluate(Set<Rule<C>> rules, C context) {
        var message = rules.stream()
                .filter(rule -> rule.isViolatedBy(context))
                .map(Rule::getMessage)
                .reduce((a, b) -> a + ", " + b);

        return message.map(RuleViolation::new)
                .orElseGet(RuleViolation::new);
    }

    public void runOrThrow(Runnable command) {
        if (message == null) {
            command.run();
        }

        throw new RuntimeException(message);
    }
}
