package org.youngmonkeys.refactoring.v2.service;

import org.springframework.stereotype.Service;

@Service
public class GreetService {

    public String greet(String name) {
        return "Greet " + name + "!";
    }
}
