package org.example.banking.domain.rule;

public interface Rule<T> {

    boolean isViolatedBy(T t);

    String getMessage();

}
