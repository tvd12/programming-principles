package org.youngmonkeys.di.v1.dependencies_injection;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MyController {
    private final MyService service;

    public void doSomething() {
        service.doSomething();
    }
}
