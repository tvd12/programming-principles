package org.youngmonkeys.di.v2.dependencies_injection;

import com.tvd12.ezyhttp.server.core.annotation.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MyService {

    private final MyRepository repository;

    public void doSomething() {
        repository.doSomething();
    }
}
