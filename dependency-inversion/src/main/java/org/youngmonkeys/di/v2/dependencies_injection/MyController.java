package org.youngmonkeys.di.v2.dependencies_injection;

import com.tvd12.ezyhttp.server.core.annotation.Controller;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MyController {
    private final MyService service;

    public void doSomething() {
        service.doSomething();
    }
}
