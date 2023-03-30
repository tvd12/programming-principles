package org.youngmonkeys.di.v1.dependencies_injection;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MyService {

    private final MyRepository repository;

    public void doSomething() {
        repository.doSomething();
    }
}
